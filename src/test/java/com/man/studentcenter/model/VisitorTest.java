package com.man.studentcenter.model;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.opt.DependentCourse;
import com.man.studentcenter.model.service.opt.IndependentCourse;
import com.man.studentcenter.model.service.opt.OptCourseElement;
import com.man.studentcenter.model.service.opt.OptVisitorImpl;
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
    private OptVisitorImpl visitor;

    @Autowired
    public void setVisitor(OptVisitorImpl visitor) {
        this.visitor = visitor;
    }

    @Test
    @Order(1)
    void testAdd() {
        OptCourseElement[] courseElements = new OptCourseElement[]{
                new IndependentCourse("COMP62521", "Agile Development"),
                new DependentCourse("COMP62421", "Querying Data", "COMP60411"),
                new IndependentCourse("COMP60411", "Modelling Data")};
        Student student = new Student();
        student.setToken(999);
        List<Course> list = addCourse(courseElements, student);
        Assert.isTrue(list.size() == 0, "TestAdd: " + getMessage(list));
    }

    String getMessage(List<Course> list) {
        StringBuffer sb = new StringBuffer();
        for (Course a : list) {
            if (a == null) continue;
            sb.append(a.toString() + " ");
        }
        return "In list:\n" + sb.toString() + "\n---------end of the list ---------";
    }

    List<Course> addCourse(OptCourseElement[] courseElements, Student student) {
        List<OptCourseElement> sortedList = new ArrayList<>();
        for (OptCourseElement element : courseElements) {
            if(element instanceof IndependentCourse) sortedList.add(0, element);
            else sortedList.add(element);
        }
        List<Course> list = new ArrayList<>();
        for (OptCourseElement element : sortedList) {
            if (element.acceptOptIn(visitor, student) == null) continue;
            System.out.println(element.acceptOptIn(visitor, student));
            list.add(element.acceptOptIn(visitor, student));
        }
        return list;
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
        List<Course> list = deleteCourse(courseElements, student);
        Assert.isTrue(list.size() == 0, "testDelete: " + getMessage(list));
    }


    List<Course> deleteCourse(OptCourseElement[] courseElements, Student student) {
        List<OptCourseElement> sortedList = new ArrayList<>();
        for (OptCourseElement element : courseElements) {
            if(element instanceof IndependentCourse) sortedList.add(element); //add to the end
            else sortedList.add(0,element); // process first
        }
        List<Course> list = new ArrayList<>();
        for (OptCourseElement element : sortedList) {
            if (element.acceptOptIn(visitor, student) == null) continue;
            System.out.println(element.acceptOptIn(visitor, student));
            list.add(element.acceptOptIn(visitor, student));
        }
        return list;
    }


}
