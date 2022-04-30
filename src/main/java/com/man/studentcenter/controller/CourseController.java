package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Data 2022/4/21 21:09
 * @Author ruary
 * @Version 1.0
 * @Describe add delete show showAll
 **/
@RestController
public class CourseController {

    private CourseMapper courseMapper;

    @RequestMapping("/addCourse")
    public String addCourse() {
        Course course = new Course("COMP13212", "Data Science");
        courseMapper.insert(course);
        return "Add: success";
    }


    @RequestMapping("/deleteCourse/{id}")
    public int deleteCourse(@PathVariable("id") String id) {
        int result = courseMapper.delete(id);
        return result;
    }

    @RequestMapping("/showCourse/{id}")
    public String showCourse(@PathVariable("id") String id) {
        Course course = courseMapper.selectById(id);
        return course.toString();
    }

    @RequestMapping("/showAllCourse")
    public String showAllCourse() {
        List<Course> alist = courseMapper.selectAll();
        StringBuffer sb = new StringBuffer();
        for (Course a : alist) {
            sb.append(a.toString() + " ");
        }
        return sb.toString();
    }

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

}
