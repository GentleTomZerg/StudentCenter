package com.man.studentcenter.model.service.composite;

public class Tag implements Html{
    private String startTag;
    private String content;
    private String endTag;

    public Tag(String startTag, String endTag, String content) {
        this.startTag = startTag;
        this.endTag = endTag;
        this.content = content;
    }

    @Override
    public String append() {
        return startTag + this.content + endTag;
    }
}
