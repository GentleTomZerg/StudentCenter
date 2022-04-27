package com.man.studentcenter.model.entity;


import com.man.studentcenter.model.service.state.State;
import java.util.Objects;

public class Student {
    Integer token;
    Integer status;
    String username;
    String password;
    State state;

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

    @Override
    public String toString() {
        return "Student{" +
                "token=" + token +
                ", status=" + status +
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
}
