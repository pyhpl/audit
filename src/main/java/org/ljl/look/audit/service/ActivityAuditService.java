package org.ljl.look.audit.service;

import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.mapper.ActivityAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityAuditService {

    @Autowired
    private ActivityAuditMapper activityAuditMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void add(ActivityAudit activityAudit) {
        activityAuditMapper.insert(activityAudit);
    }

    public List<ActivityAudit> getByState(short state) {
        return activityAuditMapper.selectByState(state);
    }

    public void update(ActivityAudit activityAudit) {
        if (activityAudit.getState() != ConstConfig.WAITING_AUDIT_STATE) {
            activityAuditMapper.updateByAuditUser(activityAudit);
        } else {
            activityAuditMapper.updateAuditUserByUuid(null, activityAudit.getUuid());
        }
    }

    public List<ActivityAudit> getsUserAudited(String token) {
        String openId = stringRedisTemplate.opsForValue().get(token);
        List<ActivityAudit> topicAudits = activityAuditMapper.selectByStateAndNullAuditUser(ConstConfig.WAITING_AUDIT_STATE);
        topicAudits.forEach(topicAudit ->
                activityAuditMapper.updateAuditUserByUuid(openId, topicAudit.getUuid())
        );
        return topicAudits;
    }

    public ActivityAudit getByActivityUuid(String activityUuid) {
        return activityAuditMapper.selectByActivityUuid(activityUuid);
    }
}
