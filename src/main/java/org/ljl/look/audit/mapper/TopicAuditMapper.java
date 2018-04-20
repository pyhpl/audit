package org.ljl.look.audit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.audit.entity.TopicAudit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicAuditMapper {

    @Insert("INSERT INTO topic_audit VALUES(#{uuid}::uuid, #{auditUser}, #{topicUuid}::uuid, #{suggestion}, #{state}, #{auditDate}, #{valid})")
    void insert(TopicAudit topicAudit);

    @Select("SELECT * FROM topic_audit WHERE state=#{state}")
    List<TopicAudit> selectByState(@Param("state") short state);
}
