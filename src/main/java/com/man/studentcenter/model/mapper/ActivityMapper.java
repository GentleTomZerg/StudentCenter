package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityMapper {
    @Insert("insert into Activity(token,aname,weekday,start,end) values(#{token},#{aname},#{weekday},#{start},#{end})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Activity activity);

    @Update("update Activity set token = #{token}, aname = #{aname}, weekday = #{weekday}, start = #{start}, end = #{end} where id = #{id}")
    Integer update(Activity activity);

    @Delete("delete from activity where id=#{id}")
    Integer delete(@Param("id") Integer id);

    @Select("select id,token,aname,weekday,start,end from Activity")
    List<Activity> selectAll();

    @Select("select id,token,aname,weekday,start,end from Activity where id=#{id}")
    Activity selectById(@Param("id") Integer id);

    @Select("select id,token,aname,weekday,start,end from Activity where token=#{token}")
    List<Activity> selectByToken(@Param("token") Integer token);
}
