package com.man.studentcenter.model.service.login;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenLogin implements LoginStrategy{
    StudentMapper studentMapper;

    @Override
    public Student login(Student student) {
        return studentMapper.selectByToken(student.getToken());
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
