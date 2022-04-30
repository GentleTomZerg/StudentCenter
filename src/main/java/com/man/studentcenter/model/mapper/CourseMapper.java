package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from Course where courseid=#{courseid}")
    Course selectById(@Param("courseid") String courseid);

    @Insert("insert into Course(courseid,cname,dependency) values(#{courseid},#{cname},#{dependency})")
    void insert(Course course);


    @Delete("delete from Course where courseid=#{courseid}")
    Integer delete(@Param("courseid") String courseid);

    @Select("select * from Course")
    List<Course> selectAll();
}
