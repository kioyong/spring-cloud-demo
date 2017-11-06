package com.yong.orders.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Created by yong.a.liang on 9/18/2017.
 */
@Slf4j
public class TimeFormatTest {

    @Test
    public void formatHoursTest(){
        String s = formatHours(0l);
        String s1 = formatHours(80l);
        String s2 = formatHours(360l);
        String s3 = formatHours(3600l);
        String s4 = formatHours(36000l);
        String s6 = formatHours(35666l);
        String s5 = formatHours(50000l);
        log.debug("s="+s);
        log.debug("s1="+s1);
        log.debug("s2="+s2);
        log.debug("s3="+s3);
        log.debug("s4="+s4);
        log.debug("s5="+s5);
        log.debug("s6="+s6);
    }


    public String formatHours(long seconds){
        long min = seconds / 60;
        long hour = 0l;
        String hourStr = "00:"+String.format("%02d",min);
        if(min>=60){
            min = (seconds / 60) % 60;
            hour = (seconds / 60 ) / 60;
            hourStr = String.format("%02d",hour)+":" +String.format("%02d",min);
        }
        return hourStr;
    }
    private static final DateTimeFormatter DATE_TIME_NANOSECONDS_OFFSET_FORMATTER =
            new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE_TIME)
                    .appendFraction(ChronoField.NANO_OF_SECOND, 0, 3, true)
                    .appendOffset("+HH:mm", "Z")
                    .toFormatter();

//    @GetMapping("/gamingDate")
//    public List<PatronSessionEntity> findByGamingDate(@RequestParam() String date){
//        //TODO timezone issue
//        date = date + "T00:00:00Z";
//        ZonedDateTime z1 = ZonedDateTime.parse(date,DATE_TIME_NANOSECONDS_OFFSET_FORMATTER);
//        ZonedDateTime nextDay = z1.plusDays(1);
//        List<PatronRatingEntity> gamingDateList = patronRatingRepository.findByGamingDateBetween(z1, nextDay);
//        if (gamingDateList == null){
//            return null;
//        }
//        List<String> ids = gamingDateList.stream().map(t -> t.getId()).collect(Collectors.toList());
//        List<PatronSessionEntity> result = patronSessionRepository.findByPatronRatingIdIn(ids);
//        return result;
//    }
}
