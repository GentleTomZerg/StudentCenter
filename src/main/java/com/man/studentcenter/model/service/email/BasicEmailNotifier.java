package com.man.studentcenter.model.service.email;

import java.util.List;

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
    public List<String> assemble() {
        stringList.clear();
        stringList.add("THIS IS AN AUTOMATIC MESSAGE");
        stringList.add("Hello!");
        stringList.add("Just as a reminder that payment is available until 15/12/2022 23:59.");
        return stringList;
    }



    @Override
    public String toString() {
        return " - This is a daily reminder for a student pending for registration.";
    }
}
