package com.man.studentcenter.model.service.adapter;


import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;

import java.util.Map;

public class JSONNewsAdapter implements NewsAdapter{
    private String json;

    public JSONNewsAdapter(String json) {
        this.json = json;
    }

    @Override
    public String getString() {
        JsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(json);
        return jsonMap.get("time").toString() + jsonMap.get("content").toString();
    }

}
