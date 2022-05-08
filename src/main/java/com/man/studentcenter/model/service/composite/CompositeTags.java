package com.man.studentcenter.model.service.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeTags implements Html{
    private String startTag;
    private String endTag;
    private List<Html> children;

    public CompositeTags(String startTag, String endTag) {
        this.startTag = startTag;
        this.endTag = endTag;
        children = new ArrayList<>();
    }

    @Override
    public String append() {
        StringBuilder sb = new StringBuilder(startTag);
        for (Html child : children) {
            sb.append(child.append());
        }
        sb.append(endTag);
        return sb.toString();
    }

    public void add(Html child) {
        children.add(child);
    }

    public Html get(int i) {return children.get(i);}
}
