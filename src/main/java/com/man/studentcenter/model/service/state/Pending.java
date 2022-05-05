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
    public void chooseCourse() {

    }

    @Override
    public void deleteCourse() {

    }

    @Override
    public void subscribe(Student student, SubscribeMapper subscribeMapper, List<String> newsletters) {
    }
}
