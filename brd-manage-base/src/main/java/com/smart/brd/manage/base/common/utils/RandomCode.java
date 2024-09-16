package com.smart.brd.manage.base.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomCode {
    private RandomCode(){

    }
    public static String getRandomCode(int digit) {
        if (digit < 1) {
            digit = 1;
        }
        Double X = (double)(Math.random() * 9 + 1);
        Double number = X * (Math.pow(10, (double) digit - 1));
        int numberInt = number.intValue();
        return String.valueOf(numberInt);
    }

    public static String getRandomCodeContainLetter(int digit) {
        String randomcode = "";
        // 用字符数组的方式随机(去掉OI)
        String model = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        //将字符串转为字符数组
        char[] m = model.toCharArray();
        if (digit < 1) {
            digit = 1;
        }
        for (int j = 0; j < digit; j++) {
            char c = m[(int) (Math.random() * 23)];
            // 保证六位随机数之间没有重复的
            if (randomcode.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            randomcode = randomcode + c;
        }
        log.info(randomcode);
        return randomcode;
    }

}
