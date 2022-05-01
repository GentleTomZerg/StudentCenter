package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.service.opt.CourseService;
import com.man.studentcenter.model.service.opt.DependentCourse;
import com.man.studentcenter.model.service.opt.OptCourseElement;
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

    @Autowired
    private CourseService courseService;

    @RequestMapping("/addCourse")
    public Integer addCourse() {
        OptCourseElement course = new DependentCourse("4", "Test for opt-in", "3");
        return courseService.add((Course) course);
    }

    @RequestMapping("/deleteCourse/{id}")
    public int deleteCourse(@PathVariable("id") String id) {
        return courseService.delete(id);
    }

    @RequestMapping("/showCourse/{id}")
    public String showCourse(@PathVariable("id") String id) {
        Course course = courseService.selectById(id);
        return course.toString();
    }

    @RequestMapping("/showAllCourse")
    public String showAllCourse() {
        List<Course> alist = courseService.selectAll();
        StringBuffer sb = new StringBuffer();
        for (Course a : alist) {
            sb.append(a.toString() + " ");
        }
        return sb.toString();
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

}
