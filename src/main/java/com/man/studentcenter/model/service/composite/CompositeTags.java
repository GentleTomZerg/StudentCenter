package com.man.studentcenter.model.service.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeTags implements Html{
    private String tag;
    private List<Html> children;

    public CompositeTags(String tag) {
        this.tag = tag;
        children = new ArrayList<>();
    }

    @Override
    public String append() {
        StringBuilder sb = new StringBuilder(tag);
        sb.insert(1, "/");
        String endTag = sb.toString();

        sb = new StringBuilder(tag);
        for (Html child : children) {
            sb.append(child.append());
        }
        sb.append(endTag);

        return sb.toString();
    }

    public void add(Html child) {
        children.add(child);
    }
}
