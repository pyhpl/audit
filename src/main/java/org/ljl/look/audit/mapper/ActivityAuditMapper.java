package org.ljl.look.audit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.audit.entity.ActivityAudit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityAuditMapper {

    @Insert("INSERT INTO activity_audit VALUES(#{uuid}::uuid, #{auditUser}, #{activityUuid}::uuid, #{suggestion}, #{state}, #{auditDate}, #{valid})")
    void insert(ActivityAudit activityAudit);

    @Select("SELECT * FROM activity_audit WHERE state=#{state}")
    List<ActivityAudit> selectByState(@Param("state") short state);


}
