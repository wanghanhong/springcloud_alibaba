package com.smart.device.access.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

/**
 * @description: 16进制工具类
 * @author: SanDuo
 * @date: 2020/4/8 16:46
 * @version: 1.0
 */
public class HexUtil {
    private static String hexStr = "0123456789ABCDEF";
    private static String[] binaryArray = {"0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011",
            "1100", "1101", "1110", "1111"
    };

    public static Long hexToLong(String inHex) {
        return Long.parseLong(inHex, 16);
    }

    public static int alertTransfer(int m) {
        m = (m & 0x7f) | ((m >> 7 & 0x1) << 3);
        return m;
    }

    /**
     * 十六进制数转十进制
     *
     * @param hexString
     * @return
     */
    public static Double getLatLonData(String hexString) {
        Long longData = hexToLong(hexString);
//        String stringData = String.format("%.4f", (longData.doubleValue() / 1000000));
        String stringData = String.valueOf((longData.doubleValue() / 1000000));
        return Double.valueOf(stringData);
    }

    public static String getCaculateData(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        Long longData = hexToLong(hexString);
        return String.valueOf(longData);
    }

    public static String getDate(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        Long longData = hexToLong(hexString);

        return String.valueOf((longData + 1536508800) * 1000);
    }

    public static String getTimeStamp(String timeString) {
        return String.valueOf((Long.valueOf(getCaculateData(timeString)) + 1536508800) * 1000);
    }

    public static String hexToBinaryString(String hexString) {
        byte[] bytes = hexStringToBinary(hexString);
        return bytes2BinaryStr(bytes);
    }

    public static String reservse(String data) {
        if (StringUtils.isNotEmpty(data)) {
            return new StringBuilder(data).reverse().toString();
        }
        return null;
    }

    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }

    /**
     * @return 转换为二进制字符串
     */
    private static String bytes2BinaryStr(byte[] bArray) {
        String outStr = "";
        int pos = 0;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            //低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos];
        }
        return outStr;
    }

    /**
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    private static byte[] hexStringToBinary(String hexString) {
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位
        for (int i = 0; i < len; i++) {
            //右移四位得到高位
            high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte) (high | low);//高地位做或运算
        }
        return bytes;
    }

    public static Integer binaryToDecimal(String binarySource) {
        BigInteger bi = new BigInteger(binarySource, 2);    //转换为BigInteger类型
        return Integer.parseInt(bi.toString());     //转换成十进制
        //        return bi;     //转换成十进制
    }

    public static Double getVolData(String hexString) {
        Long longData = hexToLong(hexString);
        String stringData = String.format("%.2f", (longData.doubleValue() / 100));
        return Double.valueOf(stringData);
    }

    public static Double getVersionData(String hexString) {
        Long longData = hexToLong(hexString);
        String stringData = String.format("%.1f", (longData.doubleValue() / 10));
        return Double.valueOf(stringData);
    }

    public static String decode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        //将每2位16进制整数组装成一个字节
        String hexString = "0123456789ABCDEF";
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    /**
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */
    @SuppressWarnings("unused")
    private static String binaryToHexString(byte[] bytes) {
        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    //00 021A0A47 067CBF2C 24 0232 3C 00034157 29 21
    //	public static void main(String[] args) {
    //		String s = hexToBinaryString("21");
    //		System.out.println(s);
    //		Long s = hexToLong("24");
    //		String ss = String.format("%.4f", (s.doubleValue() / 1000000));
    //		System.out.println(ss);
    //		Long a = hexToLong("21");
    //		System.out.println(a);
    //		System.out.println("00021A0A47067CBF2C2402323C000341572921".length());
    //	}
}
