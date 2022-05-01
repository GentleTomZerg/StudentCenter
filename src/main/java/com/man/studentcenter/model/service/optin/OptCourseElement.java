package com.man.studentcenter.model.service.optin;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe visitor pattern - optional courses
 **/
public interface OptCourseElement  {
    Course accept(OptInVisitor visitor, Student student);
}
