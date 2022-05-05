package com.man.studentcenter.model.service.newsletter;

public interface Observer {
    void update();
    void setNewsLetter(AbstractNewsletter instance);
}
