package com.man.studentcenter.controller;

import com.man.studentcenter.model.service.newsletter.AbstractNewsletter;
import com.man.studentcenter.model.service.newsletter.NewsletterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsletterSimulatorController {
    private NewsletterFactory newsletterFactory;

    @Autowired
    public void setNewsletterFactory(NewsletterFactory newsletterFactory) {
        this.newsletterFactory = newsletterFactory;
    }

    @RequestMapping("/manchester/news")
    public String notifyAllTheSubscribers() {
        AbstractNewsletter instance = newsletterFactory.buildNewsLetter("My Manchester News");
        instance.notifySubscribers();
        return "success";
    }
}
