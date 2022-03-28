package com.man.studentcenter.controller;

import com.man.studentcenter.model.mapper.StudentMapper;
import com.man.studentcenter.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private StudentMapper studentMapper;

    @RequestMapping("/addStudent")
    public String addUser() {
        Student student = new Student("Tom", "111");
        studentMapper.insert(student);
        return "success!";
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
