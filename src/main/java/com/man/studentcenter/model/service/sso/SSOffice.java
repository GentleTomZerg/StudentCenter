package com.man.studentcenter.model.service.sso;

import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SelectionMapper;
import com.man.studentcenter.model.mapper.StudentMapper;
import com.man.studentcenter.model.service.newsletter.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SSOffice
 * @Data 2022/5/7 21:16
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Component
public class SSOffice {
    final int REGISTERED = 2;
    List<Observer> registrars = new ArrayList<>();

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SelectionMapper selectionMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Autowired
    public void setSelectionMapper(SelectionMapper selectionMapper) {
        this.selectionMapper = selectionMapper;
    }

    public String addCourse(Student student, Course course) {
        refreshRegistrarsList();
        if (!registrars.contains(student))
            return "No Permission To Student:" + student.getToken();
        Selection selection = selectionMapper.selectByTokenCourseId(student.getToken(),course.getCourseid());
        if (selection != null)
            return "No repetition required.";
        selection = new Selection();
        selection.setToken(student.getToken());
        selection.setCourseid(course.getCourseid());
        return selectionMapper.insert(selection) == 1 ? "Added successfully" : "Failed to add";
    }

    public String deleteCourse(Student student, Course course) {
        refreshRegistrarsList();
        if (!registrars.contains(student))
            return "No Permission To Student:" + student.getToken();
        Selection selection = selectionMapper.selectByTokenCourseId(student.getToken(),course.getCourseid());
        if (selection == null)
            return "Failed: " + course.toString() + " does not exist in the student's schedule.";
        return selectionMapper.deleteByTokenCourseId(student.getToken(),course.getCourseid()) == 1 ? "Deleted  successfully" : "Failed to delete";
    }


    public List<Observer> refreshRegistrarsList() {
        if (registrars != null) registrars.clear();
        List<Student> students = studentMapper.selectByStatus(REGISTERED);
        registrars.addAll(students);
        return registrars;
    }

    public boolean ifAuthorised(Student student){
        refreshRegistrarsList();
        return registrars.contains(student);
    }
}
