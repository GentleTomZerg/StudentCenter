package com.man.studentcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String doHello() {
        return "Hello Spring!";
    }
}
