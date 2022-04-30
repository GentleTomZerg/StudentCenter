package com.man.studentcenter.model.entity;

/**
 * @Data 2022/4/21 20:18
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
public class Newsletter {
    Integer nid;
    String nname;

    public Newsletter(Integer nid, String nname) {
        this.nid = nid;
        this.nname = nname;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "nid=" + nid +
                ", nname='" + nname + '\'' +
                '}';
    }
}
