package com.smart.device.access.common.utils;

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

    public static void main(String[] args) {
        System.out.println(12);
    }
}
