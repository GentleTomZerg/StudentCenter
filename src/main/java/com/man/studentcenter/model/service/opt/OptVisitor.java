package com.man.studentcenter.model.service.opt;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe visit addtional courses that the student wants to add
 **/
@Component
public interface OptVisitor {
    Course visitIn(IndependentCourse course, Student student);
    Course visitIn(DependentCourse course, Student student);
    Course visitOut(IndependentCourse course, Student student);
    Course visitOut(DependentCourse course, Student student);
}
