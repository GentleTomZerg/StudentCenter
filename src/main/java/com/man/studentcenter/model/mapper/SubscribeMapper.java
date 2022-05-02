package com.man.studentcenter.model.mapper;

import com.man.studentcenter.model.entity.Newsletter;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.entity.Subscribe;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    /**
     * Operation Insert
     *
     * @param subscribe instance of Subscribe
     */
    @Insert("insert into subscribe(token, nid) values(#{token},#{nid})")
    void insert(Subscribe subscribe);

    /**
     * Operation Delete
     *
     * @param  subscribe instance of Subscribe
     * @return num of effected lines
     */
    @Delete("delete from subscribe where token=#{token} and nid=#{nid}")
    int deleteByTokenAndNid(Subscribe subscribe);

    /**
     * 查询所有
     *
     * @return a list of all tokens subscribed nid
     */
    @Select("select token, nid from subscribe where nid = #{nid}")
    List<Subscribe> selectByNid(@Param("nid") int nid);

    /**
     * 找到所有订阅了nid的学生
    */
    @Select("select student.token, status from student, subscribe where student.token = subscribe.token and nid = #{nid};")
    List<Student> selectSubscribedStudents(@Param("nid") int nid);

    /**
     * 找到某个学生订阅的所有newsletter
     */
    @Select("select newsletter.nid, nname from subscribe, newsletter where newsletter.nid = subscribe.nid and subscribe.token = #{token};")
    List<Newsletter> selectNewsLettersSubscribedByStudent(@Param("token") int token);


}
