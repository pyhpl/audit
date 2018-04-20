package org.ljl.look.audit.service;

import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.mapper.ActivityAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityAuditService {

    @Autowired
    private ActivityAuditMapper activityAuditMapper;

    public void add(ActivityAudit activityAudit) {
        activityAuditMapper.insert(activityAudit);
    }

    public List<ActivityAudit> getByState(short state) {
        return activityAuditMapper.selectByState(state);
    }

    public void update(ActivityAudit activityAudit) {

    }

}
