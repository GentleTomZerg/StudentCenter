package com.man.studentcenter.model.service.email;

/**
 * @ClassName IcsEmailNotifier
 * @Data 2022/5/1 1:18
 * @Author ruary
 * @Version 1.0
 * @Describe Concrete Decorators – ICS
 *              The message is formatted in such a way that it expects the recipient to
 *              send the invite - not be invited, nor add to his/her calendar.
 *              Extending the base decorator functionality
 *              and modifying the component behavior accordingly.
 **/
public class IcsEmailNotifier extends NotifierDecorator {

    private String ics;

    public IcsEmailNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(toString());
    }

    @Override
    public String toString() {
        return "Adding features of IcsEmailNotifier{}.";
    }

    public String getIcs() {
        return ics;
    }

    public void setIcs(String ics) {
        this.ics = ics;
    }
}
