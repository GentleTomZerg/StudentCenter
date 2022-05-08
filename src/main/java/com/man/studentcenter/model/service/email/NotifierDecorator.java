package com.man.studentcenter.model.service.email;

import java.util.List;

/**
 * @ClassName NotifierDecorator
 *
 * @Data 2022/5/1 1:11
 * @Author ruary
 * @Version 1.0
 * @Describe  Implement the interface
 *            and it has a HAS-A relationship with the interface.
 **/
public class NotifierDecorator implements Notifier {

    protected Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public List<String> assemble() {
        return this.notifier.assemble();
    }



    @Override
    public String toString() {
        return "NotifierDecorator{}";
    }
}
