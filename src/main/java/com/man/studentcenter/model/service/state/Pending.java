package com.man.studentcenter.model.service.state;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pending implements State {

    @Override
    public void getTimeTable() {

    }

    @Override
    public List<String> chooseCourse(List<String> courseids, Student student) {
        return null;
    }
    @Override
    public List<String> deleteCourse(List<String> courseids, Student student) {
        return null;
    }

    @Override
    public void subscribe(Student student, SubscribeMapper subscribeMapper, List<String> newsletters) {
    }
}
