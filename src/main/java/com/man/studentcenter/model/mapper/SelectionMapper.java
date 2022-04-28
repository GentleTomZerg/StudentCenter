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
    void insert(Selection selection);

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
}
