package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Newsletter;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SubscribeMapper;
import com.man.studentcenter.model.service.login.LoginStrategy;
import com.man.studentcenter.model.service.login.PasswordLogin;
import com.man.studentcenter.model.service.login.TokenLogin;
import com.man.studentcenter.model.service.newsletter.AbstractNewsletter;
import com.man.studentcenter.model.service.newsletter.NewsletterFactory;
import com.man.studentcenter.model.service.state.Pending;
import com.man.studentcenter.model.service.state.Registered;
import com.man.studentcenter.model.service.state.State;
import com.man.studentcenter.model.service.state.Unregistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    private LoginStrategy loginStrategy;
    private TokenLogin tokenLogin;
    private PasswordLogin passwordLogin;
    private Registered registered;
    private Pending pending;
    private Unregistered unregistered;
    private SubscribeMapper subscribeMapper;
    private NewsletterFactory newsletterFactory;

    @Autowired
    public void setNewsletterFactory(NewsletterFactory newsletterFactory) {
        this.newsletterFactory = newsletterFactory;
    }

    @Autowired
    public void setSubscribeMapper(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }

    @Autowired
    public void setTokenLogin(TokenLogin tokenLogin) {
        this.tokenLogin = tokenLogin;
    }

    @Autowired
    public void setPasswordLogin(PasswordLogin passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    @Autowired
    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    @Autowired
    public void setPending(Pending pending) {
        this.pending = pending;
    }

    @Autowired
    public void setUnregistered(Unregistered unregistered) {
        this.unregistered = unregistered;
    }

    @RequestMapping("/")
    public String login(Model model, HttpSession session) {
        // User has already login, redirect to index
        if (session.getAttribute("student") != null) {
            model.addAttribute("page", "index");
            return "index";
        }
        Student student = new Student();
        model.addAttribute("student", student);
        return "login";
    }


    @RequestMapping(value = "/login")
    public ModelAndView login(@ModelAttribute("student") Student postStudent,
                              HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // No session found
        setLoginStrategy(postStudent);
        Student student = this.loginStrategy.login(postStudent);
        // When a student login
        // His state will init
        // His subscribe list will init
        if (student != null) {
            student.setState(getStudentState(student.getStatus()));
            initSubscribeList(student, subscribeMapper.selectNewsLettersSubscribedByStudent(student.getToken()));
            session.setAttribute("student", student);
            mv.addObject("page", "index");
            mv.setViewName("index");
        } else {
            mv.setViewName("login");
            mv.addObject("errorMessage", "Login Failed");
        }

        return mv;
    }

    @RequestMapping("/logout")
    public String login(HttpSession session) {
        session.removeAttribute("student");
        return "redirect:/";
    }


    public void setLoginStrategy(Student student) {
        this.loginStrategy = student.getToken() != null ? tokenLogin : passwordLogin;
    }

    public State getStudentState(int status) {
        if (status == 0) return this.unregistered;
        else if (status == 1) return this.pending;
        else return this.registered;
    }

    public void initSubscribeList(Student student, List<Newsletter> newsletters) {
        List<AbstractNewsletter> subscribeList = new ArrayList<>();

        for (Newsletter newsletter : newsletters) {
            AbstractNewsletter instance = newsletterFactory.buildNewsLetter(newsletter.getNname());
            if (!subscribeList.contains(instance))
                subscribeList.add(instance);
        }

        student.setNewsletters(subscribeList);
    }
}

