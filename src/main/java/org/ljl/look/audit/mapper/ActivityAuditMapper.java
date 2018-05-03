package org.ljl.look.audit.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.audit.entity.ActivityAudit;
import org.ljl.look.audit.mapper.sql.ActivityAuditSql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityAuditMapper {

    @Insert("INSERT INTO activity_audit VALUES(#{uuid}::uuid, #{auditUser}, #{activityUuid}::uuid, #{suggestion}, #{state}, #{auditDate}, #{valid})")
    void insert(ActivityAudit activityAudit);

    @Select("SELECT * FROM activity_audit WHERE state=#{state}")
    List<ActivityAudit> selectByState(@Param("state") short state);

    @Select("SELECT * FROM activity_audit WHERE state=#{state} AND audit_user IS NULL")
    List<ActivityAudit> selectByStateAndNullAuditUser(@Param("state") short state);

    @Update("UPDATE activity_audit SET audit_user=#{auditUser} WHERE uuid=#{uuid}::uuid")
    void updateAuditUserByUuid(@Param("auditUser") String auditUser, @Param("uuid") String uuid);

    @UpdateProvider(type = ActivityAuditSql.class, method = "updateByAuditUser")
    void updateByAuditUser(ActivityAudit activityAudit);

    @Select("SELECT * FROM activity_audit WHERE activity_uuid=#{activityUuid}::uuid")
    ActivityAudit selectByActivityUuid(@Param("activityUuid") String activityUuid);

    @UpdateProvider(type = ActivityAuditSql.class, method = "update")
    void update(ActivityAudit activityAudit);

}
