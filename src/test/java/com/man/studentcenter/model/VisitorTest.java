package com.man.studentcenter.model;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.opt.OptService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VisitorTest
 * @Data 2022/5/1 8:51
 * @Author ruary
 * @Version 1.0
 * @Describe To test service and visitor
 **/

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VisitorTest {

    @Autowired
    private OptService service;
    @Autowired
    public void setService(OptService service) {
        this.service = service;
    }

    @Test
    @Order(1)
    void testAdd() {
        List<String> courseids = new ArrayList<>();
        courseids.add("4");
        courseids.add("3");
        courseids.add("2");
        courseids.add("5");
        courseids.add("1");

        Student student = new Student();
        student.setToken(999);
        List<String> list = service.addCourse(courseids, student);
        Assert.isTrue(list.size() == 0, "Result: " + list);
    }




    @Test
    @Order(2)
    void testDelete() {
        List<String> courseids = new ArrayList<>();
        courseids.add("4");
        courseids.add("3");
        courseids.add("2");
        courseids.add("5");
        courseids.add("1");
        Student student = new Student();
        student.setToken(999);
        List<String> list = service.deleteCourse(courseids, student);
        Assert.isTrue(list.size() == 0, "testDelete: " + list);
    }

    String getMessage(List<Course> list) {
        StringBuffer sb = new StringBuffer();
        for (Course a : list) {
            if (a == null) continue;
            sb.append(a.toString() + " ");
        }
        return "In list:\n" + sb.toString() + "\n---------end of the list ---------";
    }

}
