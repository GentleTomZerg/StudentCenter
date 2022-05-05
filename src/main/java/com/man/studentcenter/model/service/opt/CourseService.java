package com.man.studentcenter.model.service.opt;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CourseService
 * @Data 2022/5/1 4:04
 * @Author ruary
 * @Version 1.0
 * @Describe integrating manipulations to Course
 **/
@Service
public class CourseService {


    private CourseMapper mapper;

    /***
     * @Description: Add Course to database
     * @Params: [course]
     * @Return: int  1: success; others: error
     **/
    public int add(Course course) {
        return mapper.insert(course);
    }

    /***
     * @Description: delete courses from course list
     * @Params: [id]
     * @Return: int 1: success; 0: error
     **/
    public int delete(String id) {
        return mapper.delete(id);
    }

    @Autowired
    public void setMapper(CourseMapper mapper) {
        this.mapper = mapper;
    }

    public Course selectById(String id) {
        return mapper.selectById(id);
    }

    public List<Course> selectAll() {
        return mapper.selectAll();
    }
}
