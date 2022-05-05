package com.man.studentcenter.model.entity;

import java.util.Objects;

public class Selection {
    private int id;
    private int token;
    private int courseid;

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

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
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
