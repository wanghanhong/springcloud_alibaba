package com.smart.video.manage.common.utils;


public class RandomCode {

    public static String getRandomCode(int digit) {
        if (digit < 1) {
            digit = 1;
        }
        int number = (int) ((Math.random() * 9 + 1) * (Math.pow(10, digit - 1)));
        return String.valueOf(number);
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
        System.out.println(randomcode);
        return randomcode;
    }

}
