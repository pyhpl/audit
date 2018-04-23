package org.ljl.look.audit.mapper.sql;

import org.apache.ibatis.jdbc.SQL;
import org.ljl.look.audit.entity.TopicAudit;
import org.springframework.stereotype.Component;

@Component
public class TopicAuditSql {

    public String updateByAuditUser(TopicAudit topicAudit) {
        return new SQL() {{
            UPDATE("topic_audit");
            if (topicAudit.getSuggestion() != null) {
                SET("suggestion=#{suggestion}");
            }
                SET("state=#{state}");
            if (topicAudit.getAuditDate() != null) {
                SET("audit_date=#{auditDate}");
            }
            WHERE("uuid=#{uuid}::uuid")
                    .AND().WHERE("audit_user=#{auditUser}");
        }}.toString();
    }

}
