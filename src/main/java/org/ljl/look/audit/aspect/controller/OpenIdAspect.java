package org.ljl.look.audit.aspect.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.entity.TopicAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OpenIdAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * org.ljl.look.audit.controller.ActivityAuditController.put(..))")
    public void putActivityAudit(){}

    @Pointcut("execution(public * org.ljl.look.audit.controller.TopicAuditController.put(..))")
    public void putTopicAudit(){}


    @Before("putActivityAudit()||putTopicAudit()")
    public void doBeforeWeavingOpenId(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String openId = stringRedisTemplate.opsForValue().get(request.getHeader("token"));
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
            if (arg instanceof TopicAudit) {
                TopicAudit topicAudit = ((TopicAudit) arg);
                if (topicAudit.getState() != ConstConfig.WAITING_AUDIT_STATE) {
                    topicAudit.setAuditUser(openId);
                }
            } else if (arg instanceof ActivityAudit) {
                ActivityAudit activityAudit = ((ActivityAudit) arg);
                if (activityAudit.getState() != ConstConfig.WAITING_AUDIT_STATE) {
                    activityAudit.setAuditUser(openId);
                }
            }
        });
    }

}
