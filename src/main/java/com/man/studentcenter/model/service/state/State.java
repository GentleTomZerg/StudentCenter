package com.man.studentcenter.model.service.state;

import com.man.studentcenter.model.entity.Student;

public interface State {
    void getTimeTable();
    void chooseCourse();
    void deleteCourse();
    void setSubscriber(Student student);
}
