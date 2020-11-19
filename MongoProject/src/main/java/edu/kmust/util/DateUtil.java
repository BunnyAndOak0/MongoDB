package edu.kmust.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author BunnyAndOak0
 * @Description
 * @Date 2020/11/18 8:09
 **/
public class DateUtil {
    /**
     * @Author BunnyAndOak0
     * @Description Date To String
     **/
    public static String dateToString(String pattern, Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * @Author BunnyAndOak0
     * @Description String To Date
     **/
    public static Date StringToDate(String pattern, String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date d = null;

        try {
            d = simpleDateFormat.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }

    /**
     * @Author BunnyAndOak0
     * @Description 
     **/


}
