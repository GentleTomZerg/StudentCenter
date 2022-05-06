package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * Operation Insert
     *
     * @param student instance of Student
     */
    @Insert("insert into student(token,status) values(#{token},#{status})")
    void insert(Student student);

    /**
     * Operation Update
     *
     * @param student instance of Student
     * @return 受影响的行数
     */
    @Update("update student set status=#{status} where token=#{token}")
    int update(Student student);

    /**
     * Operation Delete
     *
     * @param  token primary key of table student
     * @return 受影响的行数
     */
    @Delete("delete from student where token=#{token}")
    int delete(@Param("token") int token);

    /**
     * 查询所有
     *
     * @return a list of all students
     */
    @Select("select token, status from student")
    List<Student> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param token student token
     * @return student object whose token equals #{token}
     */
    @Select("select token, status, username, password from student where token=#{token}")
    Student selectByToken(@Param("token") int token);

    @Select("select token, status, username, password from student where username = #{username} and password = #{password};")
    Student selectByUsernameAndPassword(@Param("username") String username,
                                        @Param("password") String password);
}
