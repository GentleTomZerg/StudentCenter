package com.man.studentcenter.model.service.adapter;

public class AdapterTest {
    public static void main(String[] args) {
        String json = "{\"time\": \"9:00\", \"content\": \"Morning news\"}";

        NewsAdapter jsonNewsAdapter = new JSONNewsAdapter(json);
        System.out.println(jsonNewsAdapter.getString());

        NewsAdapter xmlNewsAdapter = new XMLNewsAdapter();
    }
}
