package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Data 2022/4/21 20:59
 * @Author ruary
 * @Version 1.0
 * @Describe show delete update 目前接收URL中请求参数 测试通过 后续调用API可改为其他接受请求参数方式
 * 如：@ModelAttribute/@RequestParam/……
 **/
@RestController
public class ActivityController {
    private ActivityMapper activityMapper;

    @RequestMapping("/addActivity")
    public String addActivity() {
        Activity activity = new Activity(123, "TuM", 2, "10:00", "10:30");
        activityMapper.insert(activity);
        return "Add: success";
    }

    @RequestMapping("/updateActivity/{id}")
    public String updateActivity(@PathVariable("id") Integer id) {
        Activity activity = new Activity(123, "TuM", 3, "10:00", "10:30");
        activity.setId(id);
        activityMapper.update(activity);
        return "Update: success";
    }

    @RequestMapping("/deleteActivity/{id}")
    public String deleteActivity(@PathVariable("id") Integer id) {
        activityMapper.delete(id);
        return "success";
    }

    @RequestMapping("/showActivity/{id}")
    public String showActivity(@PathVariable("id") Integer id) {
        Activity a = activityMapper.selectById(id);
        return a.toString();
    }

    @RequestMapping("/showAllActivity")
    public String showAllActivity() {
        List<Activity> alist = activityMapper.selectAll();
        StringBuffer sb = new StringBuffer();
        for (Activity a : alist) {
            sb.append(a.toString() + "\n");
        }
        return sb.toString();
    }

    @Autowired
    public void setActivityMapper(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

}
