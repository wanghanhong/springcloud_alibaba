package com.smart.card.common.utils;


import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

/**
 * @author dukzzz
 * @date 2021/4/12 9:20:上午
 * @desc
 */
public class Md5Utils {
//    public static void main(String[] args) {
//        Md5Utils Main = new Md5Utils();
//        //视频需要的json
//        String encryption = Main.encryption("5n01793f&&{\"accesstype\":1,\"deviceid\":\"61010102031320000049\",\"memberkey\":\"LnxohaEZ1l5Q6Ja1MBTC\",\"networktype\":0}");
//        System.out.println(encryption);
//
//    }
    public String encryption(String plainText) {
        String md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                //转换为大写字母
                buf.append(Integer.toHexString(i).toUpperCase());
            }
            md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
