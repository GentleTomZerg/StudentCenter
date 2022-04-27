package com.man.studentcenter.model.service.login;

import com.man.studentcenter.model.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class PasswordLogin implements LoginStrategy{
    @Override
    public Student login(int token, String usernameAndPassword) {
        return null;
    }
}
