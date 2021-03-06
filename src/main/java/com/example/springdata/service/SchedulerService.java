package com.example.springdata.service;

import com.example.springdata.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource("application.properties")
public class SchedulerService {

    private final UserService userService;
    private final EmailService emailService;

    @Scheduled(cron = "${cron}")
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<User> list = userService.findAllByBirthDay(month, day);
        list.forEach(user -> {
            try {
                String message = "Happy Birthday dear " + user.getName() + "!";
                emailService.send(user.getEmail(), "Happy Birthday!", message);
                log.info("Email have been sent. User id: {}, Date: {}", user.getId(), date);
            } catch (Exception e) {
                log.error("Email can't be sent.User's id: {}, Error: {}", user.getId(), e.getMessage());
                log.error("Email can't be sent", e);
            }
        });
    }
}
