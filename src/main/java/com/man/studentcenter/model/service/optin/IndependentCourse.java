package com.man.studentcenter.model.service.optin;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;

/**
 * @ClassName IndependentCourse
 * @Data 2022/5/1 3:12
 * @Author ruary
 * @Version 1.0
 * @Describe Courses - a  different type of Optional Courses
 *              that needs no prerequisite.
 **/
public class IndependentCourse extends Course implements OptCourseElement  {



    public IndependentCourse(String courseid, String cname) {
        super(courseid, cname);
    }


    @Override
    public Course accept(OptInVisitor visitor, Student student) {
        return visitor.visit(this,student);
    }
}
