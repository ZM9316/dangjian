package a9chou.com.fangjiazhuangApp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date: 2017/12/4.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class TimeUtil {
    private static String split(String date) {

        String[] dateArray = date.split("");
        String[] yearDateArray = dateArray[0].split("-");

        String[] timeDateArray = dateArray[1].split(":");

        String year = yearDateArray[0];
        String month = yearDateArray[1];
        String day = yearDateArray[2];
        String hour = timeDateArray[0];
        String minute = timeDateArray[1];
        String second = timeDateArray[2];

        return year + month + day + hour + minute + second;
    }


    public static final long DateToSeconds(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

        long millionSeconds = 0;//毫秒
        try {
            millionSeconds = sdf.parse(TimeUtil.split(date)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millionSeconds;
    }

    public static String getTime1(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return format.format(date);
    }

}
