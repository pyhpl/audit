package org.ljl.look.audit.mapper.sql;

import org.apache.ibatis.jdbc.SQL;
import org.ljl.look.audit.entity.TopicAudit;
import org.springframework.stereotype.Component;

@Component
public class TopicAuditSql {

    public String update(TopicAudit topicAudit) {
        return new SQL() {{
            INSERT_INTO("activity_audit");
            if (topicAudit.getAuditUser() != null) {
                VALUES("audit_user", "#{auditUser}");
            }
            if (topicAudit.getTopicUuid() != null) {
                VALUES("topic_uuid", "#{topicUuid}");
            }
            if (topicAudit.getSuggestion() != null) {
                VALUES("suggestion", "#{suggestion}");
            }
                VALUES("state", "#{state}");
            if (topicAudit.getAuditDate() != null) {
                VALUES("audit_date", "#{auditDate}");
            }
            WHERE("uuid=#{uuid}").AND().WHERE("valid=${@org.ljl.look.audit.configuration.ConstConfig@VALID}");
        }}.toString();
    }

}
