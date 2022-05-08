package com.man.studentcenter.model.service.state;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Unregistered implements State {

    @Override
    public String getTimeTable(Student student) {
        return null;
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

    @Override
    public int addMeeting(Activity activity, List<Student> list) {
        return 0;
    }

    @Override
    public int addGroupStudy(Activity activity, List<Student> list) {
        return 0;
    }


}
