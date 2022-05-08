package com.man.studentcenter.model.service.composite;

import com.man.studentcenter.model.entity.Activity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class StudentTimetable {
    String weekdayStartTag = "<h4><a class=\"mt-2 mb-2\">";
    String weekdayEndTag = "</a></h4>";
    String tableStartTag = "<table style=\"table-layout: fixed\" class=\"table table-hover\">\n" +
            "                                <thead>\n" +
            "                                <tr>\n" +
            "                                    <th scope=\"col\">#</th>\n" +
            "                                    <th scope=\"col\">Time</th>\n" +
            "                                    <th scope=\"col\">Name</th>\n" +
            "                                </tr>\n" +
            "                                </thead>\n" +
            "                                <tbody>";
    String tableEndTag = "</tbody>\n" +
            "                            </table>";

    public String buildTimeTable(List<Activity> activities) {
        CompositeTags monday = buildWeekday("Monday");
        CompositeTags tuesday = buildWeekday("Tuesday");
        CompositeTags wednesday = buildWeekday("Wednesday");
        CompositeTags thursday = buildWeekday("Thursday");
        CompositeTags firday = buildWeekday("Friday");
        // init Mandatory courses
        ((CompositeTags) monday.get(1)).add(buildItem("1000", "5:00 - 6:00", "COMP0001"));
        ((CompositeTags) tuesday.get(1)).add(buildItem("1001", "5:00 - 6:00", "COMP0002"));
        ((CompositeTags) wednesday.get(1)).add(buildItem("1002", "5:00 - 6:00", "COMP0003"));
        ((CompositeTags) thursday.get(1)).add(buildItem("1003", "5:00 - 6:00", "COMP0004"));
        ((CompositeTags) firday.get(1)).add(buildItem("1004", "5:00 - 6:00", "COMP0005"));

        CompositeTags timetable = new CompositeTags("", "");

        for (Activity activity : activities) {
            Integer weekday = activity.getWeekday();
            CompositeTags item = buildItem(activity.getId().toString(),
                    activity.getStart() + " - " + activity.getEnd(),
                    activity.getAname());
            switch (weekday) {
                case 1:
                    ((CompositeTags) monday.get(1)).add(item);
                    break;
                case 2:
                    ((CompositeTags) tuesday.get(1)).add(item);
                    break;
                case 3:
                    ((CompositeTags) wednesday.get(1)).add(item);
                    break;
                case 4:
                    ((CompositeTags) thursday.get(1)).add(item);
                    break;
                case 5:
                    ((CompositeTags) firday.get(1)).add(item);
                    break;
                default:
                    System.out.println("Error in StudentTimetable.java");
                    break;
            }
        }

        timetable.add(monday);
        timetable.add(tuesday);
        timetable.add(wednesday);
        timetable.add(thursday);
        timetable.add(firday);

        return timetable.append();
    }

    public CompositeTags buildWeekday(String weekday) {
        CompositeTags day = new CompositeTags("<div class=\"ms-3\">", "</div>");
        Tag head = new Tag(weekdayStartTag, weekdayEndTag, weekday);
        CompositeTags table = new CompositeTags(tableStartTag, tableEndTag);
        day.add(head);
        day.add(table);
        return day;
    }

    public CompositeTags buildItem(String number, String time, String name) {
        CompositeTags item = new CompositeTags("<tr>", "</tr>");
        Tag itemHead = new Tag("<th scope=\"row\">", "</th>", number);
        Tag itemData = new Tag("<td>", "</td>", time);
        Tag itemData2 = new Tag("<td>", "</td>", name);
        item.add(itemHead);
        item.add(itemData);
        item.add(itemData2);
        return item;
    }

    public void updateFrontEnd(String content) {
        File oldFile = new File("src/main/resources/templates/timetable.html");
        File newFile = new File(oldFile.getAbsolutePath() + ".tmp");
        boolean shouldBeDeleted = false;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(oldFile));
            PrintWriter pw = new PrintWriter(newFile);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().equals("<!-- End of Code Replacement -->")) {
                    shouldBeDeleted = false;
                    pw.println("<!-- Replace Your Code Here -->");
                    pw.println(content);
                    pw.println("<!-- End of Code Replacement -->");
                    pw.flush();
                    continue;
                }

                if (shouldBeDeleted)
                    continue;
                if (line.trim().equals("<!-- Replace Your Code Here -->")) {
                    shouldBeDeleted = true;
                    continue;
                }


                pw.println(line);
                pw.flush();
            }

            pw.close();
            bufferedReader.close();

            if (!oldFile.delete()) {
                System.out.println("删除timetable.html失败");
            }

            if (!newFile.renameTo(oldFile)) {
                System.out.println("重命名失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
