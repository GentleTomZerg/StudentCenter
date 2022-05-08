package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Activity;
import com.man.studentcenter.model.entity.Course;
import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SelectionMapper;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.opt.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

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
        List<Activity> activities = student.getTimetable();
        mv.addObject("activityList", activities);
        return mv;
    }

    @RequestMapping(value = "/choose", method = RequestMethod.POST)
    public ModelAndView chooseCourse(@ModelAttribute("selList") List<String> courseids, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        List<String> errorCourseIds = student.chooseCourse(courseids);
        mv.addObject("errorIds", errorCourseIds);
        mv.addObject("page", "opt");
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteCourse(@ModelAttribute("selList") List<String> courseids, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Student student = session.getAttribute("student") == null
                ? null
                : (Student) session.getAttribute("student");
        if (student == null) {
            mv.setViewName("login");
            return mv;
        }

        List<String> errorCourseIds = student.deleteCourse(courseids);
        mv.addObject("page", "opt");
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
        List<String> selectList = new ArrayList<>();
        mv.addObject("selList", selectList);
        List<String> deleteList = new ArrayList<>();
        mv.addObject("delList", deleteList);
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

}
