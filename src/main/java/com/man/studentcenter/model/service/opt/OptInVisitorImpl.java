package com.man.studentcenter.model.service.opt;

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
 * @Version 1.1
 * @Describe every course will have it’s own logic to add.
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

    /***
     * @Description: add the course to student's selection without checking
     * @Params: [course, student]
     * @Return: error: Course; null: success
     * */
    @Override
    public Course visit(IndependentCourse course, Student student) {
        return mapper.insert(new Selection(student.getToken(),course.getCourseid())) == 1? null:course;
    }

    /***
     * @Description: Check if the student is eligible before add
     * @Params: [course, student]
     * @Return: error: Course; null: success
     **/
    @Override
    public Course visit(DependentCourse course, Student student) {
        if(mapper.selectByTokenCourseId(student.getToken(),course.getDependency())==null)
        return course;
        else return mapper.insert(new Selection(student.getToken(),course.getCourseid())) == 1? null:course;
    }


}
