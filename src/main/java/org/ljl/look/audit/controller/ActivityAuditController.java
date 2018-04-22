package org.ljl.look.audit.controller;

import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.service.ActivityAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityAuditController {

    @Autowired
    private ActivityAuditService activityAuditService;

    @GetMapping("/api/activity-audit")
    @ResponseStatus(HttpStatus.OK)
    public ActivityAudit get(@RequestParam(required = false, defaultValue = "") String activityUuid) {
        if (!activityUuid.equals("")) {
            return activityAuditService.getByActivityUuid(activityUuid);
        }
        return null;
    }

    @PostMapping("/api/activity-audit")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody ActivityAudit activityAudit) {
        activityAuditService.add(activityAudit);
        return new HttpHeaders() {{
            set("uuid", activityAudit.getUuid());
        }};
    }

    @GetMapping("/api/activity-audit/s")
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityAudit> gets(@RequestParam(required = false, defaultValue = "") String state) {
        if (state.equals(ConstConfig.WAITING_AUDIT_STATE_STR)) {
            return activityAuditService.getByState(ConstConfig.WAITING_AUDIT_STATE);
        }
        return null;
    }

    @PutMapping("/api/activity-audit/s")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void put(@Validated @RequestBody ActivityAudit activityAudit) {
        activityAuditService.update(activityAudit);
    }
}
