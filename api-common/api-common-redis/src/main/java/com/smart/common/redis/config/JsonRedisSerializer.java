package com.smart.common.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import java.nio.charset.Charset;

/**
 * @description: Json序列化
 * @author: SanDuo
 * @date: 2020/5/23 17:54
 * @version: 1.0
 */
public class JsonRedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String str = new String(bytes, DEFAULT_CHARSET);
        try {
            return mapper.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}