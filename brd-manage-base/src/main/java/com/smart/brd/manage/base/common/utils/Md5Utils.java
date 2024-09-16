package com.smart.brd.manage.base.common.utils;


import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
/**
 * @author dukzzz
 * @date 2021/4/12 9:20:上午
 * @desc
 */
public class Md5Utils {

    public String encryption(String plainText) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
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
