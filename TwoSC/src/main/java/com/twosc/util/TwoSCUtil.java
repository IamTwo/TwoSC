package com.twosc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Jie Xiang on 14-2-7.
 */
public class TwoSCUtil {
    public static String formatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(date);
    }
}
