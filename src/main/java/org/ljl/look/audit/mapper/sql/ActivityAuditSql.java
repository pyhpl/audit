package org.ljl.look.audit.mapper.sql;

import org.apache.ibatis.jdbc.SQL;
import org.ljl.look.audit.entity.ActivityAudit;
import org.springframework.stereotype.Component;

@Component
public class ActivityAuditSql {

    public String update(ActivityAudit activityAudit) {
        return new SQL() {{
            INSERT_INTO("activity_audit");
            if (activityAudit.getAuditUser() != null) {
                VALUES("audit_user", "#{auditUser}");
            }
            if (activityAudit.getActivityUuid() != null) {
                VALUES("activity_uuid", "#{activityUuid}");
            }
            if (activityAudit.getSuggestion() != null) {
                VALUES("suggestion", "#{suggestion}");
            }
                VALUES("state", "#{state}");
            if (activityAudit.getAuditDate() != null) {
                VALUES("audit_date", "#{auditDate}");
            }
                VALUES("valid", "#{valid}");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

}
