package com.man.studentcenter.model.service.composite;

public class MandatoryTimetable {
    public static void main(String[] args) {
        CompositeTags madatoryTimetable = new CompositeTags("<div>");

        CompositeTags COMP0001 = new CompositeTags("<div>");
        COMP0001.add(new Tag("<p>", "Monday"));
        COMP0001.add(new Tag("<p>", "COMP0001"));
        COMP0001.add(new Tag("<p>", "9:00 - 12:00"));

        CompositeTags COMP0002 = new CompositeTags("<div>");
        COMP0002.add(new Tag("<p>", "Monday"));
        COMP0002.add(new Tag("<p>", "COMP0002"));
        COMP0002.add(new Tag("<p>", "1:00 - 4:00"));

        madatoryTimetable.add(COMP0001);
        madatoryTimetable.add(COMP0002);

        System.out.println(madatoryTimetable.append());

        /** 经过排版后的输出结果
         * <div>
         *     <div>
         *         <p>Monday</p>
         *         <p>COMP0001</p>
         *         <p>9:00 - 12:00</p>
         *     </div>
         *     <div>
         *         <p>Monday</p>
         *         <p>COMP0002</p>
         *         <p>1:00 - 4:00</p>
         *     </div>
         * </div>
         */

    }
}
