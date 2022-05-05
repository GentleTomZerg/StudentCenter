package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Selection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelectionMapper {
    /**
     * Operation Insert
     *
     * @param selection instance of selection
     */
    @Insert("insert into selection(token, courseid) values(#{token},#{courseid})")
    int insert(Selection selection);

    /**
     * Operation Update
     *
     * @param selection instance of selection
     * @return 受影响的行数
     */
    @Update("update selection set token=#{token}, courseid=#{courseid} where id=#{id}")
    int update(Selection selection);

    /**
     * Operation Delete
     *
     * @param  id primary key of table selection
     * @return 受影响的行数
     */
    @Delete("delete from selection where id=#{id}")
    int delete(@Param("id") int id);

    /**
     * 查询所有
     *
     * @return a list of all selections
     */
    @Select("select id, token, courseid from selection")
    List<Selection> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id student id
     * @return selection object whose id equals #{id}
     */
    @Select("select id, token, courseid from selection where id=#{id}")
    Selection selectById(@Param("id") int id);

    /***
     * @Description: Query selections by token
     * @Params: [token]
     * @Return: List<Selection>: What courses does the student select >
    **/
    @Select("select id, token, courseid from selection where token=#{token}")
    List<Selection> selectAllByToken(@Param("token") int token);

    /***
     * @Description: Query selections by token and courseid
     * @Params: [token, courseid]
     * @Return: Selection: when the student have selected this course; null: not yet
    **/
    @Select("select id, token, courseid from selection where token=#{token} and courseid=#{courseid}")
    Selection selectByTokenCourseId(@Param("token") int token, @Param("courseid") String courseid);

    /***
     * @Description: Opt-out
     * @Params: [token, courseid]
     * @Return: int 1: success; 0: error
    **/
    @Delete("delete from selection where token=#{token} and courseid=#{courseid} ")
    int deleteByTokenCourseId(@Param("token") int token, @Param("courseid") String courseid);
}
