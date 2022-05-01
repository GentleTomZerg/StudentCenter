package com.man.studentcenter.model;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.optin.*;
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

    @Test
    @Order(1)
    void testAdd() {
        OptCourseElement[] courseElements = new OptCourseElement[]{
                new IndependentCourse("COMP60411", "Modelling Data"),
                new IndependentCourse("COMP62521", "Agile Development"),
                new DependentCourse("COMP62421", "Querying Data", "COMP60411")};

        Student student = new Student();
        student.setToken(999);
        List<Course> list = addCourse(courseElements, student);
        Assert.isTrue(list.size() == 3, "success");

    }

    @Test
    @Order(2)
    void testDelete() {
        OptCourseElement[] courseElements = new OptCourseElement[]{
                new IndependentCourse("COMP60411", "Modelling Data"),
                new IndependentCourse("COMP62521", "Agile Development"),
                new DependentCourse("COMP62421", "Querying Data", "COMP60411")};

        Student student = new Student();
        student.setToken(999);
        int sum = deleteCourse(courseElements, student);
        Assert.isTrue(sum == 3, "success");

    }

    @Autowired
    private SelectionService service;


    private int deleteCourse(OptCourseElement[] courseElements, Student student) {
        OptInVisitor visitor = new OptInVisitorImpl();
        int sum = 0;
        for (OptCourseElement element : courseElements) {
            sum += service.delete(student.getToken(), element.accept(visitor, student).getCourseid());
            System.out.println(element.accept(visitor, student));
        }
        return sum;
    }


    private List<Course> addCourse(OptCourseElement[] courseElements, Student student) {
        OptInVisitor visitor = new OptInVisitorImpl();
        List<Course> list = new ArrayList<>();

        for (OptCourseElement element : courseElements) {

            list.add(new Course(element.accept(visitor, student).getCourseid(), element.accept(visitor, student).getCname()));
            System.out.println(element.accept(visitor, student));

            /*
            Selection selection = new Selection();
            selection.setCourseid(element.getCourseid());
            selection.setToken(student.getToken());
            sum += service.add(selection);*/
        }
        return list;
    }

}
