package com.man.studentcenter.model.service.email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public List<String> assemble() {
        super.assemble();
        stringList.add(toString());
        return stringList;
    }

    @Override //Adding features of IcsEmailNotifier{}.
    public String toString() {
        Date d = new Date();
        DateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm");
        String s = sdf.format(d);
        return s;
    }

    public String getIcs() {
        return ics;
    }

    public void setIcs(String ics) {
        this.ics = ics;
    }
}
