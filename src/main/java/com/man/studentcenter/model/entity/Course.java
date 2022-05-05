package com.man.studentcenter.model.entity;

import org.springframework.lang.Nullable;

/**
 * @Data 2022/4/21 20:15
 * @Author ruary
 * @Version 1.0
 * @Describe Course表对照实体
 **/
public class Course {
    String courseid;
    String cname;
    @Nullable
    String dependency;

    public Course() {
    }

    public Course(String courseid, String cname, String dependency) {
        this.courseid = courseid;
        this.cname = cname;
        this.dependency = dependency;
    }

    public Course(String courseid, String cname) {
        this.courseid = courseid;
        this.cname = cname;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseid='" + courseid + '\'' +
                ", cname='" + cname + '\'' +
                ", dependency='" + dependency + '\'' +
                '}';
    }
}
