package com.man.studentcenter.model.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName DailyReminderService
 * @Data 2022/5/7 23:17
 * @Author ruary
 * @Version 1.0
 * @Describe
 **/
@Component
public class DailyReminderService {

    private static final Logger logger = LoggerFactory.getLogger(DailyReminderService.class);

    @Scheduled(fixedRate = 60 * 1000)
    public List<String> scheduled(){
        Notifier email = new RequireReplyEmailNotifier(new IcsEmailNotifier(new BasicEmailNotifier()));
        logger.info("Execution Time: {} Task: Reminder ",LocalDateTime.now());
        return email.assemble();
    }
}
