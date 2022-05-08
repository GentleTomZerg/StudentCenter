package com.man.studentcenter.model.service.email;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SAOffice
 * @Data 2022/5/9 0:37
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Component
public class SAOffice {
    final int REGISTERED = 1;
    List<Integer> registrars = new ArrayList<>();

    @Autowired
    private StudentMapper studentMapper;


    public List<Integer> refreshRegistrarsList() {
        if (registrars != null) registrars.clear();
        List<Student> students = studentMapper.selectByStatus(REGISTERED);
        for(Student student:students){
            registrars.add(student.getToken());
        }
        return registrars;
    }


}
