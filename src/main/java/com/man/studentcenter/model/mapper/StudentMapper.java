package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * Operation Insert
     *
     * @param student instance of Student
     */
    @Insert("insert into Student(username,password) values(#{username},#{password})")
    @Options(useGeneratedKeys = true, keyColumn = "username", keyProperty = "username")
    void insert(Student student);

    /**
     * Operation Update
     *
     * @param student instance of Student
     * @return 受影响的行数
     */
    @Update("update Student set username=#{name},password=#{age} where id=#{id}")
    Long update(Student student);

    /**
     * Operation Delete
     *
     * @param id student id
     * @return 受影响的行数
     */
    @Delete("delete from person where id=#{id}")
    Long delete(@Param("id") Long id);

    /**
     * 查询所有
     *
     * @return a list of students
     */
    @Select("select id,name,age from person")
    List<Student> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id student id
     * @return student object whose id equals #{id}
     */
    @Select("select id,name,age from person where id=#{id}")
    Student selectById(@Param("id") Long id);
}
