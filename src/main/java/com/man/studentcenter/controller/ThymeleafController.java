package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class ThymeleafController {
    private StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @RequestMapping("/allstudents")
    public ModelAndView getAllStudents() {
        ModelAndView mv = new ModelAndView();
        List<Student> students = studentMapper.selectAll();
        mv.addObject("students", students);
        mv.setViewName("allstudents");
        return mv;
    }
}
