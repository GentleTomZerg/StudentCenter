package com.man.studentcenter.model.entity;

/**
 * @Data 2022/4/21 20:10
 * @Author ruary
 * @Version 1.0
 * @Describe activity表对照实体
 **/
public class Activity {
    Integer id;
    Integer token;
    String aname;
    Integer weekday;
    String start;
    String end;

    public Activity() {
    }

    public Activity(Integer token, String aname, Integer weekday, String start, String end) {
        this.token = token;
        this.aname = aname;
        this.weekday = weekday;
        this.start = start;
        this.end = end;
    }

    public Activity(Integer id, Integer token, String aname, Integer weekday, String start, String end) {
        this.id = id;
        this.token = token;
        this.aname = aname;
        this.weekday = weekday;
        this.start = start;
        this.end = end;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", token=" + token +
                ", aname='" + aname + '\'' +
                ", weekday=" + weekday +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
