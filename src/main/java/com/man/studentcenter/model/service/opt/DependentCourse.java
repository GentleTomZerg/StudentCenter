package com.man.studentcenter.model.service.opt;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;

/**
 * @ClassName DependentCourse
 * @Data 2022/5/1 3:21
 * @Author ruary
 * @Version 1.0
 * @Describe Course - a  different type of Optional Courses
 *              that you must complete a prerequisite before you can take
 **/
public class DependentCourse extends Course implements OptCourseElement {
    public DependentCourse(String courseid, String cname, String dependency) {
        super(courseid, cname, dependency);
    }

    @Override
    public Course acceptOptIn(OptVisitor visitor, Student student) {
        return visitor.visitIn(this, student);
    }

    @Override
    public Course acceptOptOut(OptVisitor visitor, Student student) {
        return visitor.visitOut(this,student);
    }


}
