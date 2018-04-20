package org.ljl.look.audit.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.util.UuidTool;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class CommonAttributeAspect {

    @Pointcut("execution(public * org.ljl.look.audit.service.TopicAuditService.add(..))")
    public void addTopicAudit(){}

    @Pointcut("execution(public * org.ljl.look.audit.service.ActivityAuditService.add(..))")
    public void addActivityAudit(){}


    @Before("addTopicAudit()||addActivityAudit()")
    public void doBeforeAdd(JoinPoint joinPoint) throws Exception {
        Object arg = joinPoint.getArgs()[0];
        if (arg instanceof TopicAudit) { // TopicAudit
            TopicAudit topicAudit = (TopicAudit) arg;
            topicAudit.setUuid(UuidTool.getValue());
            topicAudit.setAuditDate(new Date());
            topicAudit.setValid(ConstConfig.UNVALID);
        } else if (arg instanceof ActivityAudit) {
            ActivityAudit activityAudit = (ActivityAudit) arg;
            activityAudit.setUuid(UuidTool.getValue());
            activityAudit.setAuditDate(new Date());
            activityAudit.setValid(ConstConfig.UNVALID);
        }
    }
}














