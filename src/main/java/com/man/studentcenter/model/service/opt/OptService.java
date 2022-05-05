package com.man.studentcenter.model.service.opt;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OptService
 * @Data 2022/5/1 19:55
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Component
public class OptService {

    @Autowired
    private OptVisitorImpl visitor;

    @Autowired
    public void setVisitor(OptVisitorImpl visitor) {
        this.visitor = visitor;
    }

    public String getMessage(List<Course> list) {
        StringBuffer sb = new StringBuffer();
        for (Course a : list) {
            if (a == null) continue;
            sb.append(a.toString() + " ");
        }
        return "In list:\n" + sb.toString() + "\n---------end of the list ---------";
    }

    public List<String> deleteCourse(List<String> courseIds, Student student) {
        List<OptCourseElement> sortedList = visitor.sortedCourses(courseIds, false);
        List<Course> errorCourses = new ArrayList<>();
        List<String> errorCids = new ArrayList<>();
        for (OptCourseElement element : sortedList) {
            Course course = element.acceptOptOut(visitor, student);
            if (course == null) continue;
            System.out.println(course.toString());
            errorCourses.add(course);
            errorCids.add(course.getCourseid());
        }
        return errorCids;
    }

    
    /***
     * @Description: Once error appear, exit code -1;
     * @Params: [courseIds, student]
     * @Return: java.util.List<java.lang.String>
    **/
    public List<String> addCourse(List<String> courseIds, Student student) {
        List<OptCourseElement> sortedList = visitor.sortedCourses(courseIds, true);
        List<Course> errorCourses = new ArrayList<>();
        List<String> errorCids = new ArrayList<>();
        for (OptCourseElement element : sortedList) {
            Course course = element.acceptOptIn(visitor, student);
            if (course == null) continue;
            System.out.println(course.toString());
            errorCourses.add(course);
            errorCids.add(course.getCourseid());
        }
        return errorCids;
    }


}
