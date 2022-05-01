package com.man.studentcenter.model.service.optin;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName OptInVisitorImpl
 * @Data 2022/5/1 3:28
 * @Author ruary
 * @Version 1.0
 * @Describe every item will have it’s own logic to add.
 * @BugsToFix service Nullpointer
 *
 **/
public class OptInVisitorImpl implements OptInVisitor {

    @Autowired
    private SelectionService service;

    /***
     * @Description: add the course to student's selection
     * @Params: [course, student]
     * @Return: error: Course; null: success
     **/
    @Override
    public Course visit(IndependentCourse course, Student student) {
         /*
        Selection selection = new Selection();
        selection.setCourseid(course.getCourseid());
        selection.setToken(student.getToken());

        service.add(selection);

          */
         return course;
    }

    /***
     * @Description: Check if the student is eligible before add
     * @Params: [course, student]
     * @Return: error: Course; null: success
    **/
    @Override
    public Course visit(DependentCourse course, Student student) {
         /*
        int token = student.getToken();
        String dependency = course.getDependency();
        if (service.ifSelected(token, dependency)) {
            Selection selection = new Selection();
            selection.setToken(token);
            selection.setCourseid(dependency);
             service.add(selection);


            return 1;


        } else {
            return course;

        }// else if (学生待选列表里有前置课程的情况)


          */
         return course;
    }

    @Autowired
    public void setService(SelectionService service) {
        this.service = service;
    }
}
