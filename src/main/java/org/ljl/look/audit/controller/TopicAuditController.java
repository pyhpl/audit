package org.ljl.look.audit.controller;

import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.service.TopicAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicAuditController {

    @Autowired
    private TopicAuditService topicAuditService;

    @PostMapping("/api/topic-audit")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody TopicAudit topicAudit) {
        topicAuditService.add(topicAudit);
        return new HttpHeaders() {{
            set("uuid", topicAudit.getUuid());
        }};
    }

    @GetMapping("/api/topic-audit")
    @ResponseStatus(HttpStatus.OK)
    public TopicAudit get(@RequestParam(required = false, defaultValue = "") String topicUuid) {
        if (!topicUuid.equals("")) {
            return topicAuditService.getByTopicUuid(topicUuid);
        }
        return null;
    }

    @GetMapping("/api/topic-audit/s")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicAudit> gets(@RequestParam(required = false, defaultValue = "") String state) {
        if (state.equals(ConstConfig.WAITING_AUDIT_STATE_STR)) {
            return topicAuditService.getByState(ConstConfig.WAITING_AUDIT_STATE);
        }
        return null;
    }

    @GetMapping("/api/user/topic-audit/s")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicAudit> getsUserAudited(@RequestHeader("token") String token) {
        return topicAuditService.getsUserAudited(token);
    }

    @PutMapping("/api/topic-audit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void put(@Validated @RequestBody TopicAudit topicAudit) {
        topicAuditService.update(topicAudit);
    }

}
