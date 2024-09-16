package com.smart.test.demo.service.message;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author 三多
 * @Time 2020/4/30
 */
@Service
public class ReceiveService {
    /**
     * 默认是input，在Sink类中指定，如果想要多个input，需要写一个实现Sink的类
     *
     * @param receiveMsg
     */
    @StreamListener("input")
    public void receiveInput1(String receiveMsg) {
        System.out.println("input receive: " + receiveMsg);
    }

}
