package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.newsletter.Observer;
import com.man.studentcenter.model.service.sso.SSOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SSOSimulatorController
 * @Data 2022/5/7 20:07
 * @Author ruary
 * @Version 1.0
 * @Describe Simulate SSO to add and remove
 **/
@RestController
public class SSOSimulatorController {

    @Autowired
    private SSOffice ssOffice;

    @Autowired
    public void setSsOffice(SSOffice ssOffice) {
        this.ssOffice = ssOffice;
    }

    @RequestMapping("/sso")
    public String notifyAllTheSubscribers() {
         List<Observer> list = ssOffice.refreshRegistrarsList();
         System.out.println(list);
         return "success";
    }

    @RequestMapping("/sso/addCourse")
    public String addCourse() {
        Student student = new Student();
        student.setToken(888);
        Course course = new Course();
        course.setCourseid("1");
        return ssOffice.addCourse(student, course);
    }

    @RequestMapping("/sso/deleteCourse") //@PathVariable("id") String id
    public String deleteCourse() {
        Student student = new Student();
        student.setToken(888);
        Course course = new Course();
        course.setCourseid("1");
        return ssOffice.deleteCourse(student, course);
    }


}
