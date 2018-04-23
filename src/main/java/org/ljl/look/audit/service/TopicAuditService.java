package org.ljl.look.audit.service;

import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.mapper.TopicAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicAuditService {

    @Autowired
    private TopicAuditMapper topicAuditMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void add(TopicAudit topicAudit) {
        topicAuditMapper.insert(topicAudit);
    }

    public List<TopicAudit> getByState(short state) {
        return topicAuditMapper.selectByState(state);
    }

    public TopicAudit getByTopicUuid(String uuid) {
        return topicAuditMapper.selectByTopicUuid(uuid);
    }

    public void update(TopicAudit topicAudit) {
        if (topicAudit.getState() != ConstConfig.WAITING_AUDIT_STATE) {
            topicAuditMapper.updateByAuditUser(topicAudit);
        } else {
            topicAuditMapper.updateAuditUserByUuid(null, topicAudit.getUuid());
        }
    }

    public List<TopicAudit> getsUserAudited(String token) {
        String openId = stringRedisTemplate.opsForValue().get(token);
        List<TopicAudit> topicAudits = topicAuditMapper.selectByStateAndNullAuditUser(ConstConfig.WAITING_AUDIT_STATE);
        topicAudits.forEach(topicAudit ->
            topicAuditMapper.updateAuditUserByUuid(openId, topicAudit.getUuid())
        );
        return topicAudits;
    }
}
