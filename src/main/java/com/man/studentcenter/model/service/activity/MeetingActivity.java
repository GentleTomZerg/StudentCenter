package com.man.studentcenter.model.service.activity;

import com.man.studentcenter.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MeetingActivity
 * @Data 2022/5/7 17:33
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/

@Component
public class MeetingActivity extends ActivityTemplate {
    @Override
    public void setParticipant(List<Student> list) {
        System.out.println(list.get(0));
        super.activity.setToken(list.get(0).getToken());
    }

    @Override
    public int addActivity() {
        return mapper.insert(super.activity);
    }
}
