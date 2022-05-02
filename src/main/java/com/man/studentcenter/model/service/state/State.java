package com.man.studentcenter.model.service.state;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.StudentMapper;
import com.man.studentcenter.model.mapper.SubscribeMapper;

import java.util.List;

public interface State {
    void getTimeTable();
    void chooseCourse();
    void deleteCourse();
    void subscribe(Student student, SubscribeMapper subscribeMapper, List<String> newsletters);
}
