package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Newsletter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsletterMapper {
    @Select("select * from Newsletter where nid=#{nid}")
    Newsletter selectById(@Param("nid") Integer nid);

    @Insert("insert into Newsletter(nid,nname) values(#{nid},#{nname})")
    void insert(Newsletter newsletter);

    @Delete("delete from Newsletter where nid=#{nid}")
    Integer delete(@Param("nid") Integer nid);

    @Select("select * from Newsletter")
    List<Newsletter> selectAll();
}
