package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.email.DailyReminderService;
import com.man.studentcenter.model.service.sso.SSOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

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
        System.out.println("time table");
        System.out.println(student);
        student.getTimetable();
        return mv;
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
    public ModelAndView deleteCourse(List<String> courseids, HttpSession session) {
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

    @Autowired
    private SSOffice ssOffice;

    @Autowired
    public void setSsOffice(SSOffice ssOffice) {
        this.ssOffice = ssOffice;
    }

    @RequestMapping("/permission")
    public ModelAndView getPermission(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");

        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        System.out.println(student);

        mv.addObject("page", "permission");
        String message = new String();
        if (ssOffice.ifAuthorised(student)) {
            message = "You allow the student support office to add or remove courses from your course list.";
        } else {
            message = "SSO has no access for your course list.";
        }
        mv.addObject("message", message);
        mv.setViewName("permission");
        return mv;
    }

    @Autowired
    private DailyReminderService reminderService;

    @RequestMapping("/reminder")
    public ModelAndView getRemind(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");

        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        mv.addObject("page", "reminder");
        mv.setViewName("emailReminder");
        if (student.getStatus() == 1) {
            List<String> stringList = reminderService.scheduled();//前端展示该信息 List<String>
            mv.addObject("emailString", stringList);
        } else {
            mv.addObject("emailString", null);
        }
        return mv;
    }

    @RequestMapping(value = "/addMeeting", method = RequestMethod.POST)
    public ModelAndView addMeeting(@ModelAttribute("meeting") Activity meeting, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        mv.addObject("page", "timetable");

        if (!student.addMeeting(meeting)) {
            mv.addObject("errorMessage", "Add failed.");
        }
        mv.setViewName("timetable");
        return mv;
    }

    @RequestMapping(value = "/addGroupstudy", method = RequestMethod.POST)
    public ModelAndView addGroupStudy(@ModelAttribute("meeting") Activity activity, @ModelAttribute("students") List<Student> list, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        mv.addObject("page", "timetable");
        if (!student.addGroupStudy(activity, list)) {
            mv.addObject("errorMessage", "Add failed.");
        }
        mv.setViewName("timetable");
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
