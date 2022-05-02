package com.man.studentcenter.model.service.newsletter;

import com.man.studentcenter.model.entity.Newsletter;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.entity.Subscribe;
import com.man.studentcenter.model.mapper.StudentMapper;
import com.man.studentcenter.model.mapper.SubscribeMapper;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractNewsletter {
    List<Observer> subscribers = new ArrayList<>();
    String name;
    String news;

    public String getName() {
        return name;
    }


    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public void subscribe(Student student, int nid, SubscribeMapper subscribeMapper) {
        if (subscribers.contains(student))
            return;
        Subscribe subscribe = new Subscribe();
        subscribe.setToken(student.getToken());
        subscribe.setNid(nid);
        subscribeMapper.insert(subscribe);
        refershSubscibersList(nid, subscribeMapper);
    }

    public void unsubscirbe(Student student, int nid, SubscribeMapper subscribeMapper) {
        if (!subscribers.contains(student))
            return;
        Subscribe subscribe = new Subscribe();
        subscribe.setToken(student.getToken());
        subscribe.setNid(nid);
        subscribeMapper.deleteByTokenAndNid(subscribe);
        refershSubscibersList(nid, subscribeMapper);
    }

    public void refershSubscibersList(int nid, SubscribeMapper subscribeMapper) {
        if (subscribers != null) subscribers.clear();
        List<Student> students = subscribeMapper.selectSubscribedStudents(nid);
        subscribers.addAll(students);
        for (Observer subscriber : subscribers) {
            subscriber.setNewsLetter(this);
        }
    }

    public abstract void notifySubscribers();

}
