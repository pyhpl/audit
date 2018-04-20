package org.ljl.look.audit.service;

import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.mapper.TopicAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicAuditService {

    @Autowired
    private TopicAuditMapper topicAuditMapper;

    public void add(TopicAudit topicAudit) {
        topicAuditMapper.insert(topicAudit);
    }

    public List<TopicAudit> getByState(short state) {
        return topicAuditMapper.selectByState(state);
    }

}
