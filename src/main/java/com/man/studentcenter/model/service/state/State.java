package com.man.studentcenter.model.service.state;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SubscribeMapper;

import java.util.List;

public interface State {
    String getTimeTable(Student student);
    List<String> chooseCourse(List<String> courseids, Student student);
    List<String> deleteCourse(List<String> courseids, Student student);
    void subscribe(Student student, SubscribeMapper subscribeMapper, List<String> newsletters);
}
