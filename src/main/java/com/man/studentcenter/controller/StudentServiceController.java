package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SelectionMapper;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.activity.MeetingActivity;
import com.man.studentcenter.model.service.email.DailyReminderService;
import com.man.studentcenter.model.service.newsletter.AbstractNewsletter;
import com.man.studentcenter.model.service.opt.CourseService;
import com.man.studentcenter.model.service.sso.SSOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

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
        Activity activity = new Activity();
        mv.addObject("activity", activity);
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
        mv.setViewName("notification");
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
        mv.setViewName("redirect:/timetable");
        return mv;
    }

    @RequestMapping(value = "/addGroupstudy", method = RequestMethod.POST)
    public ModelAndView addGroupStudy(@ModelAttribute("meeting") Activity activity, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        System.out.println(activity);
        List<Student> list = new ArrayList<>();
        Student student1 = new Student();
        student1.setToken(888);
        list.add(student1);
        System.out.println(list);
        mv.addObject("page", "timetable");
        int resultCode = student.addGroupStudy(activity, list);
        if (resultCode!=1) {
            mv.addObject("errorMessage", "Participants:"+resultCode+" Cannot be invited.");
        }
        mv.setViewName("redirect:/timetable");
        return mv;
    }






    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectionMapper selectionMapper;

    @RequestMapping(value = "/opt")
    public ModelAndView getOpt(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        mv.addObject("page", "opt");
        List<Course> courseList = courseService.selectAll();
        mv.setViewName("optionalcourse");

        List<Selection> selections = selectionMapper.selectAllByToken(student.getToken());
        List<Course> selectedCourses = new ArrayList<Course>();
        for (Selection selection : selections) {
            Course course = courseService.selectById(selection.getCourseid());
            selectedCourses.add(course);
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get(i).getCourseid().equals(course.getCourseid())) {
                    courseList.remove(i);
                    break;
                }
            }
        }
        mv.addObject("selections", selectedCourses);
        mv.addObject("courseList", courseList);
        return mv;
    }

    @RequestMapping(value = "/choose", method = RequestMethod.POST)
    public ModelAndView chooseCourse(@RequestParam("choose") List<String> choosen, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        List<String> errorCourseIds = student.chooseCourse(choosen);
        mv.setViewName("redirect:/opt");
        mv.addObject("errorIds", errorCourseIds);
        mv.addObject("page", "opt");
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteCourse(@RequestParam("delete") List<String> courseids, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        System.out.println(courseids);
        List<String> errorCourseIds = student.deleteCourse(courseids);
        mv.addObject("errorCourseIds", errorCourseIds);
        mv.addObject("page", "opt");
        mv.setViewName("redirect:/opt");
        return mv;
    }

    @RequestMapping("/newsletters")
    public ModelAndView newsletter(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        List<AbstractNewsletter> newsletters = student.getNewsletters();
        int[] subscribed = new int[4];
        for(AbstractNewsletter newsletter:newsletters){
            if(Objects.equals(newsletter.getName(), "My Manchester News")){
                subscribed[0] = 1;
            } else if (Objects.equals(newsletter.getName(), "The Careers News")){
                subscribed[1] = 1;
            } else if (Objects.equals(newsletter.getName(), "Stellify")){
                subscribed[2] = 1;
            } else if (Objects.equals(newsletter.getName(), "Sport News")){
                subscribed[3] = 1;
            }
        }
        mv.addObject("subed", subscribed);
        mv.addObject("page", "news");
        mv.setViewName("newsletters");
        return mv;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ModelAndView setSubscriber(HttpSession session, @RequestParam("sub") List<String> subscription) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }
        List<String> sub_str = new ArrayList<>();
        for(String i: subscription){
            switch (i) {
                case "1":
                    sub_str.add("My Manchester News");
                    break;
                case "2":
                    sub_str.add("The Careers News");
                    break;
                case "3":
                    sub_str.add("Stellify");
                    break;
                case "4":
                    sub_str.add("Sport News");
                    break;
            }
        }
        System.out.println(sub_str);
        student.subscribe(subscribeMapper, sub_str);
        student.update();
        mv.setViewName("redirect:/newsletters");
        return mv;
    }

}
