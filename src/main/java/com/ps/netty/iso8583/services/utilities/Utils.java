package com.ps.netty.iso8583.services.utilities;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    private static String PATTERN = "YDDDHHSSSSSS";
    private static String TIMEZONE_IST = "IST";
    private static String TIMEZONE_GMT = "GMT";
    private final String ZERO = "0";
    private int id =0;


    public static String getDateTime(String pattern, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone(timeZone == TimeZone.GMT ? TIMEZONE_GMT : TIMEZONE_IST));
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public String generateRRN(){
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR)%10;
        int julianDay = calendar.get(Calendar.DAY_OF_YEAR);
        String unqiueId = String.valueOf(uniqueId());
        return sb.append(year)
                .append(julianDay)
                .append(calendar.get(Calendar.HOUR_OF_DAY))
                .append(StringUtils.leftPad(unqiueId,6,ZERO))
                .toString();
    }

    private int uniqueId(){
        return id++;
    }
}
