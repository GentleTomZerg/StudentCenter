package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.composite.StudentTimetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentServiceController {
    private SubscribeMapper subscribeMapper;

    @Autowired
    public void setSubscribeMapper(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }

    @RequestMapping("/timetable")
    public ModelAndView getTimetable(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");

        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        mv.addObject("page", "timetable");
        student.getTimetable();
        return mv;
    }

    @RequestMapping(value = "/timetable", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> ajaxTimetable(HttpSession session) {
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        Map<String, String> map = new HashMap<>();
        map.put("timetable", student.getTimetable());
        return map;
    }

    @RequestMapping("/choose/course")
    public ModelAndView chooseCourse(List<String> courseids, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        student.chooseCourse(courseids);
        return mv;
    }

    @RequestMapping("/delete/course")
    public ModelAndView deleteCourse(List<String> courseids,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        student.deleteCourse(courseids);
        return mv;
    }

    @RequestMapping("/subscribe")
    public ModelAndView setSubscriber(HttpSession session, @RequestBody String userChoices) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        String[] splittedUserChoice = userChoices.trim().split(",");
        for (int i = 0; i < splittedUserChoice.length; i++)
            splittedUserChoice[i] = splittedUserChoice[i].trim();

        List<String> newsletterList = Arrays.asList(splittedUserChoice);
        student.subscribe(subscribeMapper, newsletterList);
        student.update();
        return mv;
    }
}
