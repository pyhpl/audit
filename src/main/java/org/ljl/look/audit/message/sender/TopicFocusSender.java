package org.ljl.look.audit.message.sender;

import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.TopicFocus;
import org.ljl.look.audit.message.wrapper.MessageWrapper;
import org.ljl.look.audit.util.JsonTool;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicFocusSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendToAdd(TopicFocus topicFocus) {
        rabbitTemplate.convertAndSend(
                ConstConfig.QUEUE_TOPIC_FOCUS,
                JsonTool.toJson(
                        MessageWrapper.builder().method(MessageWrapper.MessageMethod.POST).body(topicFocus).build()
                )
        );
    }

}

