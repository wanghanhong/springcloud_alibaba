package com.smart.device.message.common.utils;

import java.util.regex.Pattern;

/**
 * @author l
 */
public class DataUtils {

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        String regex = "^[-\\+]?\\d*[.]\\d+$";
        Pattern pattern = Pattern.compile(regex); // 之前这里正则表达式错误，现更正
        boolean flag1 = pattern.matcher(str).matches();

        boolean flag2 = isNumeric(str);
       if(flag1 || flag2){
           return true;
       }
        return false;
    }

    public static void main(String[] args) {
        DataUtils util = new DataUtils();
        String str = "12.34";

        boolean flag11 = isNumeric(str);
        System.out.println(flag11);
        boolean flag2 = isDouble(str);
        System.out.println(flag2);
    }
}
