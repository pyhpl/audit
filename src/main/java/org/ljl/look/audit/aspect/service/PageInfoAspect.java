package org.ljl.look.audit.aspect.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.util.JsonTool;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class PageInfoAspect {

    @Pointcut("execution(public * org.ljl.look.audit.service.TopicAuditService.get*(..))")
    public void getTopic(){}

    @Pointcut("execution(public * org.ljl.look.audit.service.ActivityAuditService.get*(..))")
    public void getActivity(){}

    @Before("getTopic()||getActivity()")
    public void doBeforeAdd(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getQueryString().contains(ConstConfig.PAGE_INFO_JSON_STR)) {
            Map<String, String> pageInfoMap = JsonTool.fromJson(request.getParameter(ConstConfig.PAGE_INFO_JSON_STR), new TypeReference<Map<String, String>>() {});
            PageHelper.startPage(
                    Integer.valueOf(pageInfoMap.getOrDefault(ConstConfig.PAGE_NUM, ConstConfig.DEFAULT_PAGE_NUM)),
                    Integer.valueOf(pageInfoMap.getOrDefault(ConstConfig.PAGE_SIZE, ConstConfig.DEFAULT_PAGE_SIZE))
            );
            if (pageInfoMap.containsKey("orderBy")) {
                PageHelper.orderBy(pageInfoMap.get("orderBy"));
            }
        }
    }

}
