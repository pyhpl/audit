package org.ljl.look.audit.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.message.sender.TopicFocusSender;
import org.ljl.look.audit.message.wrapper.Message;
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
        Message<TopicAudit> topicAuditMessage = JsonTool.fromJson(topicAuditMessageJson, new TypeReference<Message<TopicAudit>>() {});
        switch (topicAuditMessage.getMethod()) {
            case POST:
                topicAuditService.add(topicAuditMessage.getBody());
                break;
            case PUT:
                topicAuditService.update(topicAuditMessage.getBody());
                break;
        }
    }

}
