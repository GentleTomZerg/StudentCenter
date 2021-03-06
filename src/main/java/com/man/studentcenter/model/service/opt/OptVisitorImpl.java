package com.man.studentcenter.model.service.opt;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.CourseMapper;
import com.man.studentcenter.model.mapper.SelectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OptVisitorImpl
 * @Data 2022/5/1 3:28
 * @Author ruary
 * @Version 1.1
 * @Describe every course will have it’s own logic to add.
 **/

@Component
public class OptVisitorImpl implements OptVisitor {


    /***
     * @Description: add the course to student's selection without checking
     * @Params: [course, student]
     * @Return: error: Course; null: success
     * */
    @Override
    public Course visitIn(IndependentCourse course, Student student) {
        return selectionMapper.insert(new Selection(student.getToken(), course.getCourseid())) == 1 ? null : course;
    }

    /***
     * @Description: Check if the student is eligible before add
     * @Params: [course, student]
     * @Return: error: Course; null: success
     **/
    @Override
    public Course visitIn(DependentCourse course, Student student) {
        if (selectionMapper.selectByTokenCourseId(student.getToken(), course.getDependency()) == null)
            return course;
        else
            return selectionMapper.insert(new Selection(student.getToken(), course.getCourseid())) == 1 ? null : course;
    }

    @Autowired
    public void setSelectionMapper(SelectionMapper selectionMapper) {
        this.selectionMapper = selectionMapper;
    }

    @Autowired
    private SelectionMapper selectionMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    /***
     * @Description: To Opt-out an independentCourse requires to check if it is a prerequisite.
     * @Params: [course, student]
     * @Return: com.man.studentcenter.model.entity.Course
     **/
    @Override
    public Course visitOut(IndependentCourse course, Student student) {
        int token = student.getToken();
        List<Course> subsequents = courseMapper.selectByDependency(course.getCourseid());
        for (Course c:subsequents
             ) {
            if(selectionMapper.selectByTokenCourseId(student.getToken(),c.getCourseid())!=null) return course;
        }
        return selectionMapper.deleteByTokenCourseId(token, course.getCourseid()) == 1 ? null : course;
    }

    /***
     * @Description: Drop any subsequentCourse without checking
     * @Params: [course, student]
     * @Return: null: success; entity.Course: error
     **/
    @Override
    public Course visitOut(DependentCourse course, Student student) {
        return selectionMapper.deleteByTokenCourseId(student.getToken(), course.getCourseid()) == 1 ? null : course;
    }

    public List<OptCourseElement> sortedCourses(List<String> courseIds,boolean ifIndependentGoFirst) {
        List<OptCourseElement> elements = new ArrayList<>();
        for (String id : courseIds) {
            Course course = courseMapper.selectById(id);
            String name = course.getCname();
            String dependency = course.getDependency();
            if(ifIndependentGoFirst) {
                if (dependency == null) elements.add(0, new IndependentCourse(id, name)); // add to the head
                else elements.add(new DependentCourse(id, name, dependency));
            }else{
                if (dependency == null) elements.add(new IndependentCourse(id, name));
                else elements.add(0,new DependentCourse(id, name, dependency)); // add to the head
            }
        }
        for (OptCourseElement e:elements
             ) {
            System.out.println(e.toString());
        }
        return elements;
    }

}
