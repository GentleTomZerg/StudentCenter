package com.man.studentcenter.model.service.activity;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName GroupStudyActivity
 * @Data 2022/5/7 17:36
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/

@Component
public class GroupStudyActivity extends ActivityTemplate {

    @Autowired
    public StudentMapper studentMapper;


    @Override
    public int setParticipant(List<Student> list) {
        for (Student student :
                list) {
            if (studentMapper.selectByToken(student.getToken()).getStatus() == 2) {
                activity.setToken(student.getToken());
                addActivity();
            }else {
                return student.getToken();
            }
        }
        return 1;
    }

}
