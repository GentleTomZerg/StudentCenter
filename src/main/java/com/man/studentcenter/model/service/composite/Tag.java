package com.man.studentcenter.model.service.composite;

public class Tag implements Html{
    private String tag;
    private String content;

    public Tag(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    @Override
    public String append() {
        StringBuilder sb = new StringBuilder(tag);
        sb.insert(1, "/");
        String endTag = sb.toString();
        return tag + this.content + endTag;
    }
}
