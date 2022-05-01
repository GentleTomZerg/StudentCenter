package com.man.studentcenter.model.service.decorator;

/**
 * @ClassName BasicEmailNotifier
 *
 * @Data 2022/5/1 1:07
 * @Author ruary
 * @Version 1.0
 * @Describe As a Basic implementation of the component interface
 **/
public class BasicEmailNotifier implements Notifier {


    @Override
    public void assemble() {
        System.out.print(toString());
    }

    @Override
    public void send(String message) {
        System.out.print(message);
        // To do:
        // send email logic business
    }

    @Override
    public String toString() {
        return "BasicEmailNotifier{}.";
    }
}
