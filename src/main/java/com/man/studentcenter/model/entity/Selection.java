package com.man.studentcenter.model.entity;

import java.util.Objects;

public class Selection {
    private int id;
    private int token;
    private String courseid;


    public Selection(int token, String courseid) {
        this.token = token;
        this.courseid = courseid;
    }

    public Selection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection selection = (Selection) o;
        return id == selection.id && token == selection.token && courseid == selection.courseid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, courseid);
    }
}
