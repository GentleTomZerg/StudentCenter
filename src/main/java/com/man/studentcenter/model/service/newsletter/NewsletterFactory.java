package com.man.studentcenter.model.service.newsletter;

import com.man.studentcenter.model.mapper.SubscribeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsletterFactory {
    private SubscribeMapper subscribeMapper;

    @Autowired
    public void setSubscribeMapper(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }

    public AbstractNewsletter buildNewsLetter(String type) {
        AbstractNewsletter instance = null;
        if (type.equals("My Manchester News")) {
            instance = MyManchesterNews.getInstance();
            instance.refershSubscibersList(1, subscribeMapper);
        }

        else if (type.equals("Stellify")) {
            instance = Stellify.getInstance();
            instance.refershSubscibersList(3, subscribeMapper);
        }

        else if (type.equals("The Careers News")) {
            instance = TheCareersNewsletter.getInstance();
            instance.refershSubscibersList(2, subscribeMapper);
        }

        else if (type.equals("Sport News")) {
            instance = SportNews.getInstance();
            instance.refershSubscibersList(4, subscribeMapper);
        }

        return instance;
    }
}
