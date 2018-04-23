package org.ljl.look.audit.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.audit.entity.TopicAudit;
import org.ljl.look.audit.mapper.sql.TopicAuditSql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicAuditMapper {

    @Insert("INSERT INTO topic_audit VALUES(#{uuid}::uuid, #{auditUser}, #{topicUuid}::uuid, #{suggestion}, #{state}, #{auditDate}, #{valid})")
    void insert(TopicAudit topicAudit);

    @Select("SELECT * FROM topic_audit WHERE state=#{state}")
    List<TopicAudit> selectByState(@Param("state") short state);

    @Select("SELECT * FROM topic_audit WHERE state=#{state} AND audit_user IS NULL")
    List<TopicAudit> selectByStateAndNullAuditUser(@Param("state") short state);

    @Select("SELECT * FROM topic_audit WHERE topic_uuid=#{topicUuid}::uuid")
    TopicAudit selectByTopicUuid(@Param("topicUuid") String topicUuid);

    @UpdateProvider(type = TopicAuditSql.class, method = "updateByAuditUser")
    void updateByAuditUser(TopicAudit topicAudit);

    @Update("UPDATE topic_audit SET audit_user=#{auditUser} WHERE uuid=#{uuid}::uuid")
    void updateAuditUserByUuid(@Param("auditUser") String auditUser, @Param("uuid") String uuid);
}
