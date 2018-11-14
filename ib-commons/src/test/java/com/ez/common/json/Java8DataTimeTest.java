package com.ez.common.json;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * ClassName: Java8DataTimeTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-7 上午9:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class Java8DataTimeTest {

    @Test
    public void TheTimeStamp() throws Exception {
//        LocalDateTime localDateTime =  LocalDateTime.now();
//
//        Clock clock = Clock.systemDefaultZone();
        Instant instant = Instant.now();
        long s = instant.getEpochSecond();
        System.out.println(s);

        long time = System.currentTimeMillis();

        System.out.println(time);
        long time2 = System.nanoTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        System.out.println(dateFormat.format(date));
        System.out.println(date.getTime());
        Date date2 = new Date(time);
        System.out.println(dateFormat.format(date2));
        Date date3 = new Date(time);
        System.out.println(dateFormat.format(date3));
    }

    @Test
    public void TheTimeStamp2() throws Exception {
//        LocalDateTime localDateTime =  LocalDateTime.now();
//
//        Clock clock = Clock.systemDefaultZone();

//        Instant instant =   Instant.parse("2018-11-7 23:59:59");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss");
//        formatter.parse("2018-11-7 23:59:59");
        LocalDateTime localDateTime = LocalDateTime.parse("2018-11-7 23:59:59", formatter);

        Instant instant = localDateTime.toInstant(ZoneOffset.of("+08:00"));
        System.out.println(instant.toEpochMilli());

        System.out.println(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2018-11-7 23:59:59");
        System.out.println(dateFormat.format(date));
        System.out.println(date.getTime());
    }
}
