package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.login.LoginStrategy;
import com.man.studentcenter.model.service.login.PasswordLogin;
import com.man.studentcenter.model.service.login.TokenLogin;
import com.man.studentcenter.model.service.state.Pending;
import com.man.studentcenter.model.service.state.Registered;
import com.man.studentcenter.model.service.state.State;
import com.man.studentcenter.model.service.state.Unregistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private LoginStrategy loginStrategy;
    private TokenLogin tokenLogin;
    private PasswordLogin passwordLogin;
    private Registered registered;
    private Pending pending;
    private Unregistered unregistered;

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
    public String login() {
        return "login";
    }


    @RequestMapping("/login")
    public ModelAndView login(int loginStrategy,
                              int token,
                              String usernameAndPassword,
                              HttpSession session) {

        ModelAndView mv = new ModelAndView();
        // User has already login, redirect to index
        if (!session.isNew()) {
            return new ModelAndView("login");
        }

        // No session found
        setLoginStrategy(loginStrategy);
        Student student = this.loginStrategy.login(token, usernameAndPassword);
        if (student != null) {
            student.setState(getStudentState(student.getStatus()));
            session.setAttribute("student", student);
            mv.setViewName("login");
        } else {
            mv.setViewName("login");
            mv.addObject("errorMessage", "Login Failed");
        }

        return mv;
    }

    public void setLoginStrategy(int loginStrategy) {
        this.loginStrategy = loginStrategy == 0 ? tokenLogin : passwordLogin;
    }

    public State getStudentState(int status) {
        if (status == 0) return this.unregistered;
        else if (status == 1) return this.pending;
        else return this.registered;
    }
}
