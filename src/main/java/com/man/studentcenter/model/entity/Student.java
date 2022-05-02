package com.man.studentcenter.model.entity;

import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.newsletter.AbstractNewsletter;
import com.man.studentcenter.model.service.newsletter.Observer;
import com.man.studentcenter.model.service.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Observer {
    Integer token;
    Integer status;
    String username;
    String password;
    State state;
    List<AbstractNewsletter> newsletters = new ArrayList<>();

    public void setState(State state) {
        this.state = state;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewsletters(List<AbstractNewsletter> newsletters) {
        this.newsletters = newsletters;
    }

    public List<AbstractNewsletter> getNewsletters() {
        return newsletters;
    }

    @Override
    public String toString() {
        return "Student{" +
                "token=" + token +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return token.equals(student.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    public void getTimetable() {
        state.getTimeTable();
    }

    public void chooseCourse() {
        state.chooseCourse();
    }

    public void deleteCourse() {
        state.deleteCourse();
    }

    public void subscribe(SubscribeMapper subscribeMapper, List<String> newsletters) {
        state.subscribe(this, subscribeMapper, newsletters);
    }

    /**TODO:
     * 用学生自己调用update查看newsletter是否有新消息测试成功
     * 用newsletter实体调用notifyAll不成功
     * 因为从工厂中直接拿出的newsletter实体并没有observers[]
     * 需要考虑为了演示方便是否需要简化这一部分的代码
    */
     @Override
    public void update() {
        for (AbstractNewsletter newsletter : newsletters) {
            System.out.println("News from " + newsletter.getName() + " to " + this.getToken() + ": " + newsletter.getNews());
        }
    }

    @Override
    public void setNewsLetter(AbstractNewsletter instance) {
        newsletters.add(instance);
    }
}
