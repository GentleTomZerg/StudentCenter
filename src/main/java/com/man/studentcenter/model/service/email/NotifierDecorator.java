package com.man.studentcenter.model.service.email;

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
    public void assemble() {
        this.notifier.assemble();
    }

    @Override
    public void send(String message) {
        // https://www.geeksforgeeks.org/send-email-using-java-program/
        // To do:
        // send email logic business
    }

    @Override
    public String toString() {
        return "NotifierDecorator{}";
    }
}
