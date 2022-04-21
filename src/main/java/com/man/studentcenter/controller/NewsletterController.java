package com.man.studentcenter.controller;

import com.man.studentcenter.model.entity.Newsletter;
import com.man.studentcenter.model.mapper.NewsletterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Data 2022/4/21 21:45
 * @Author ruary
 * @Version 1.0
 * @Describe add delete show showAll
 **/
@RestController
public class NewsletterController {
    private NewsletterMapper newsletterMapper;

    @RequestMapping("/addNewsletter")
    public String addNewsletter() {
        Newsletter newsletter = new Newsletter(2, "The Careers newsletter");
        newsletterMapper.insert(newsletter);
        return "Add: success";
    }

    @RequestMapping("/deleteNewsletter/{id}")
    public String deleteNewsletter(@PathVariable("id") Integer id) {
        newsletterMapper.delete(id);
        return "Delete: success";
    }

    @RequestMapping("/showNewsletter/{id}")
    public String showNewsletter(@PathVariable("id") Integer id) {
        Newsletter newsletter = newsletterMapper.selectById(id);
        return newsletter.toString();
    }

    @RequestMapping("/showAllNewsletter")
    public String showAllNewsletter() {
        List<Newsletter> alist = newsletterMapper.selectAll();
        StringBuffer sb = new StringBuffer();
        for (Newsletter a : alist) {
            sb.append(a.toString() + " ");
        }
        return sb.toString();
    }

    @Autowired
    public void setNewsletterMapper(NewsletterMapper newsletterMapper) {
        this.newsletterMapper = newsletterMapper;
    }
}
