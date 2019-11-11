package com.fang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * DateFormat测试
 *
 * @author fwj
 * @date 2019-10-14 10:17
 **/
public class DateFormatTest{
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    static List<String> dateStrList = Arrays.asList(
            "2018-04-01 10:00:01",
            "2018-04-02 11:00:02",
            "2018-04-03 12:00:03",
            "2018-04-04 13:00:04",
            "2018-04-05 14:00:05"
    );
    public static Date formatDate(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }

    public static void main(String[] args) {
        for (String s : dateStrList) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(DATE_FORMAT.toString() + formatDate(s));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
