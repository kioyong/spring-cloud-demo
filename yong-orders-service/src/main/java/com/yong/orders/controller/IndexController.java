package com.yong.orders.controller;

import com.yong.orders.service.UserService;
import com.yong.orders.model.User;
//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Created by yong.a.liang on 6/14/2017.
 */

@RestController
@Slf4j
public class IndexController {

    @Autowired
    UserService userService;

    private static final DateTimeFormatter DATE_TIME_NANOSECONDS_OFFSET_FORMATTER =
            new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE_TIME)
                    .appendFraction(ChronoField.NANO_OF_SECOND, 0, 3, true)
                    .appendOffset("+HH:mm", "Z")
                    .toFormatter();

    @GetMapping("/index")
    public String index(){
//        log.info("test @Slf4j annotation");
        return "hello orders-service!";
    }
    @GetMapping("/error")
    public String exceptionTest(){
//        log.info("test @Slf4j annotation");
        throw new RuntimeException("Exception Test Found!");
    }

}
