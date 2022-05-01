package com.man.studentcenter.model.service.email;

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
    public void assemble() {
        super.assemble();
        System.out.print(toString());
    }

    @Override
    public void send(String message) {
        super.send(message);
        //logic business
    }

    @Override
    public String toString() {
        return "Adding features of RequireReplyEmailNotifier{}.";
    }
}
