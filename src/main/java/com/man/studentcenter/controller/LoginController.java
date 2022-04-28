package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.login.LoginStrategy;
import com.man.studentcenter.model.service.login.PasswordLogin;
import com.man.studentcenter.model.service.login.TokenLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private LoginStrategy loginStrategy;
    private TokenLogin tokenLogin;
    private PasswordLogin passwordLogin;

    @Autowired
    public void setTokenLogin(TokenLogin tokenLogin) {
        this.tokenLogin = tokenLogin;
    }

    @Autowired
    public void setPasswordLogin(PasswordLogin passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    @RequestMapping("/")
    public ModelAndView login(int loginStrategy,
                              int token,
                              String usernameAndPassword,
                              HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        // User has already login, redirect to index
        if (!session.isNew()) {
            return new ModelAndView("login");
        }

        // No session found
        setLoginStrategy(loginStrategy);
        Student student = this.loginStrategy.login(token, usernameAndPassword);
        if (student != null) {
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
}
