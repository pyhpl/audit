package org.ljl.look.audit.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.audit.configuration.ConstConfig;
import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.message.wrapper.Message;
import org.ljl.look.audit.service.ActivityAuditService;
import org.ljl.look.audit.util.JsonTool;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_ACTIVITY_AUDIT)
public class ActivityAuditReceiver {

    @Autowired
    private ActivityAuditService activityAuditService;

    @RabbitHandler
    public void process(String activityAuditMessageJson) {
        Message<ActivityAudit> activityAuditMessage = JsonTool.fromJson(activityAuditMessageJson, new TypeReference<Message<ActivityAudit>>() {});
        switch (activityAuditMessage.getMethod()) {
            case POST:
                activityAuditService.add(activityAuditMessage.getBody());
                break;
        }

    }

}
