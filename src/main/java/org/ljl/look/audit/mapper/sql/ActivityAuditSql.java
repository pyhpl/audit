package org.ljl.look.audit.mapper.sql;

import org.apache.ibatis.jdbc.SQL;
import org.ljl.look.audit.entity.ActivityAudit;
import org.springframework.stereotype.Component;

@Component
public class ActivityAuditSql {

    public String update(ActivityAudit activityAudit) {
        return new SQL() {{
            UPDATE("activity_audit");
            if (activityAudit.getSuggestion() != null) {
                SET("suggestion", "#{suggestion}");
            }
            SET("state", "#{state}");
            if (activityAudit.getAuditDate() != null) {
                SET("audit_date", "#{auditDate}");
            }
            WHERE("uuid=#{uuid}")
                    .AND().WHERE("audit_user=#{auditUser}")
                    .AND().WHERE("valid=${@org.ljl.look.audit.configuration.ConstConfig@VALID}");
        }}.toString();
    }

}
