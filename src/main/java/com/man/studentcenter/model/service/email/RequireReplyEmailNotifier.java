package com.man.studentcenter.model.service.email;

import java.util.List;

/**
 * @ClassName RequireReplyEmailNotifier
 * @Data 2022/5/1 1:29
 * @Author ruary
 * @Version 1.0
 * @Describe Concrete Decorators –
 **/
public class RequireReplyEmailNotifier extends NotifierDecorator {
    public RequireReplyEmailNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public List<String> assemble() {
        super.assemble();
        stringList.add(toString());
        return stringList;
    }


    //Adding features of RequireReplyEmailNotifier{}.
    @Override
    public String toString() {
        return " !IMPORTANT! Please Reply us.";
    }
}
