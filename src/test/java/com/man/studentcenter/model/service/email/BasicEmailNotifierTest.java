package com.man.studentcenter.model.service.email;

import java.util.List;

class BasicEmailNotifierTest {

    public static void main(String[] args) {
        Notifier email = new RequireReplyEmailNotifier(new IcsEmailNotifier(new BasicEmailNotifier()));
        List<String> s =  email.assemble();
        System.out.println(s);
    }

}