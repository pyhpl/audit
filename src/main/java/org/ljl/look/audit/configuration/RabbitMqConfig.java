package org.ljl.look.audit.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    Queue topicAuditQueue() {
        return new Queue(ConstConfig.QUEUE_TOPIC_AUDIT);
    }

    @Bean
    Queue activityAuditQueue() {
        return new Queue(ConstConfig.QUEUE_ACTIVITY_AUDIT);
    }

    @Bean
    Queue topicFocusQueue() {
        return new Queue(ConstConfig.QUEUE_TOPIC_FOCUS);
    }
}
