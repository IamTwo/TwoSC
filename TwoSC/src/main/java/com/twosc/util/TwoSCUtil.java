package com.twosc.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Jie Xiang on 14-2-7.
 */
public class TwoSCUtil {
    /**
     * Remove none digital character from the decimal string that maybe contains
     * none digit character.
     *
     * @param decimal
     *            the decimal string.
     * @return the new digital string only contains digital character.
     */
    public static String getPlain(String decimal) {
        if (TextUtils.isEmpty(decimal)) {
            decimal = "0";
        }
        char[] ch = new char[decimal.length()];
        int index = 0;
        for (int i = 0; i < decimal.length(); i++) {
            if (Character.isDigit(decimal.charAt(i))) {
                ch[index++] = decimal.charAt(i);
            }
        }
        return String.copyValueOf(ch, 0, index);
    }
}
