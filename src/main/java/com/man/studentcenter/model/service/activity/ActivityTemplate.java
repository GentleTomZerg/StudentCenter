package com.man.studentcenter.model.service.activity;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ActivityTemplate
 * @Data 2022/5/7 16:59
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Component
public abstract class ActivityTemplate {

    protected static Activity activity;


    protected static List<Activity> activities;

    @Autowired
    protected ActivityMapper mapper;

    @Autowired
    public void setMapper(ActivityMapper mapper) {
        this.mapper = mapper;
    }

    //template method, can't override
    /***
     * @Description: 0:error
     * @Params: [name, list, day, start, end]
     * @Return: int
    **/
    public final int buildActivity(String name, List<Student> list, Integer day, String start, String end) {
        activity = new Activity();
        activities = new ArrayList<>();
        setName(name);
        setTime(day, start, end);
        return setParticipant(list);
    }

    //default implementation
    private void setName(String name) {
        activity.setAname(name);
    }

    private void setTime(Integer day, String start, String end) {
        System.out.println("Setting time");
        activity.setWeekday(day);
        activity.setStart(start);
        activity.setEnd(end);
    }

    private void printTips() {
        System.out.println("Building activity with name, participant, weekday, starting time, ending time.");
    }
    public int addActivity() {
        return mapper.insert(activity);
    }

    //methods to be implemented by subclasses
    public abstract int setParticipant(List<Student> list);



}
