package com.man.studentcenter.controller;

import com.man.studentcenter.model.mapper.StudentMapper;
import com.man.studentcenter.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class StudentController {
    private StudentMapper studentMapper;

    @RequestMapping("/student")
    public String addUser(HttpSession session) {
        if (!session.isNew()) {
            System.out.println("##########");
            System.out.println(session.getAttribute("student"));
            System.out.println("#########");
        } else {
            System.out.println("Please login again");
            Student student = studentMapper.selectByToken(123);
            session.setAttribute("student", student);
        }
        return "sucess";
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
