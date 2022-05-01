package com.man.studentcenter.model.service.optin;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SelectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName OptInVisitorImpl
 * @Data 2022/5/1 3:28
 * @Author ruary
 * @Version 1.0
 * @Describe every item will have it’s own logic to add.
 * @BugsToFix  Nullpointer
 *
 **/

@Component
public class OptInVisitorImpl implements OptInVisitor {


    @Autowired
    public void setMapper(SelectionMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private SelectionMapper mapper;

    @Override
    public Course visit(IndependentCourse course, Student student) {
        return mapper.insert(new Selection(student.getToken(),course.getCourseid())) == 1? null:course;
    }

    @Override
    public Course visit(DependentCourse course, Student student) {
        return null;
    }



    /***
     * @Description: add the course to student's selection
     * @Params: [course, student]
     * @Return: error: Course; null: success

    @Override
    public Course visit(IndependentCourse course, Student student) {

        Selection selection = new Selection();
        selection.setCourseid(course.getCourseid());
        selection.setToken(student.getToken());

        service.add(selection);

         return null;
    }
     */
    /***
     * @Description: Check if the student is eligible before add
     * @Params: [course, student]
     * @Return: error: Course; null: success
    **/
/*
    @Override
    public Course visit(DependentCourse course, Student student) {

        int token = student.getToken();
        String dependency = course.getDependency();
        if (service.ifSelected(token, dependency)) {
            Selection selection = new Selection();
            selection.setToken(token);
            selection.setCourseid(dependency);
             service.add(selection);


            return null;


        } else {
            return course;

        }// else if (学生待选列表里有前置课程的情况)



         // return course;
    }

    @Autowired
    private  SelectionService service;
    @Autowired
    public void setService(SelectionService service) {
        this.service = service;
    }



 */
}
