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
public interface OptInVisitor {
    Course visit(IndependentCourse course, Student student);
    Course visit(DependentCourse course, Student student);
}
