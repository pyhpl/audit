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

    @Select("SELECT * FROM topic_audit WHERE topic_uuid=#{topicUuid}::uuid")
    TopicAudit selectByTopicUuid(@Param("topicUuid") String topicUuid);

    @UpdateProvider(type = TopicAuditSql.class, method = "update")
    void update(TopicAudit topicAudit);
}
