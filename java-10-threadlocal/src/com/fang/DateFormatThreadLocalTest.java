package com.fang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DateFormat测试
 *
 * @author fwj
 * @date 2019-10-14 10:17
 **/
public class DateFormatThreadLocalTest {
    static ExecutorService executorService = Executors.newCachedThreadPool();
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };
    static List<String> dateStrList = Arrays.asList(
            "2018-04-01 10:00:01",
            "2018-04-02 11:00:02",
            "2018-04-03 12:00:03",
            "2018-04-04 13:00:04",
            "2018-04-05 14:00:05"
    );
    public static Date formatDate(String date) throws ParseException {
        return DATE_FORMAT.get().parse(date);
    }

    public static void main(String[] args) {
        for (String s : dateStrList) {
            executorService.execute(() -> {
                try {
                    System.out.println(DATE_FORMAT.toString() + formatDate(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
