package com.man.studentcenter.model.service.state;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.ActivityMapper;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.composite.StudentTimetable;
import com.man.studentcenter.model.service.newsletter.AbstractNewsletter;
import com.man.studentcenter.model.service.newsletter.NewsletterFactory;
import com.man.studentcenter.model.service.opt.OptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Registered implements State {
    private NewsletterFactory newsletterFactory;
    public static Map<String, Integer> newsletterNameToNid = new HashMap<>();

    @Autowired
    private OptService optService;
    @Autowired
    public void setService(OptService service) {
        this.optService = service;
    }

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    public void setActivityMapper(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    private StudentTimetable studentTimetable;
    @Autowired
    public void setStudentTimetable(StudentTimetable studentTimetable) {
        this.studentTimetable = studentTimetable;
    }

    static {
        newsletterNameToNid.put("My Manchester News", 1);
        newsletterNameToNid.put("The Careers News", 2);
        newsletterNameToNid.put("Stellify", 3);
        newsletterNameToNid.put("Sport News", 4);
    }

    @Autowired
    public void setNewsletterFactory(NewsletterFactory newsletterFactory) {
        this.newsletterFactory = newsletterFactory;
    }

    @Override
    public String getTimeTable(Student student) {
        String html = studentTimetable.buildTimeTable(activityMapper.selectByToken(student.getToken()));
        return html;
    }

    @Override
    public List<String> chooseCourse(List<String> courseids, Student student) {
        return optService.addCourse(courseids,student);
    }

    @Override
    public List<String> deleteCourse(List<String> courseids, Student student) {
        return optService.deleteCourse(courseids, student);
    }

    // Student subscribes several newsletters
    // add the subscription to database
    // and update the newletter list of the student
    @Override
    public void subscribe(Student student, SubscribeMapper subscribeMapper, List<String> newsletters) {
        for (String newsletter : newsletters) {
            AbstractNewsletter newsletterInstance = newsletterFactory.buildNewsLetter(newsletter);
            newsletterInstance.subscribe(student, newsletterNameToNid.get(newsletter), subscribeMapper);
            if (!student.getNewsletters().contains(newsletterInstance))
                student.getNewsletters().add(newsletterInstance);
        }
    }
}
