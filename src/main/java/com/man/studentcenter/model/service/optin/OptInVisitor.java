package com.man.studentcenter.model.service.optin;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
public interface OptInVisitor {
    Course visit(IndependentCourse course, Student student);
    Course visit(DependentCourse course, Student student);
}
