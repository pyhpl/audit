package org.ljl.look.audit.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.message.wrapper.MessageWrapper;
import org.ljl.look.audit.service.TopicAuditService;
import org.ljl.look.audit.util.JsonTool;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_TOPIC_AUDIT)
public class TopicAuditReceiver {

    @Autowired
    private TopicAuditService topicAuditService;

    @RabbitHandler
    public void process(String topicAuditMessageJson) {
        MessageWrapper<TopicAudit> topicAuditMessageWrapper = JsonTool.fromJson(topicAuditMessageJson, new TypeReference<MessageWrapper<TopicAudit>>() {});
        switch (topicAuditMessageWrapper.getMethod()) {
            case POST:
                topicAuditService.add(topicAuditMessageWrapper.getBody());
                break;
            case PUT:
                topicAuditService.update(topicAuditMessageWrapper.getBody());
                break;
        }
    }

}
