package com.man.studentcenter.model.service.login;

import com.man.studentcenter.model.entity.Student;

public interface LoginStrategy {
    Student login(Student student);
}
