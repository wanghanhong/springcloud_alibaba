package com.smart.card.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class CardSysApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) throws JsonProcessingException, JSONException {
        JSONObject jsonObject = new JSONObject();
        String str = "{\"name\":\"5t\"}";

        JSONObject studentJSONObject = new JSONObject(str);


        ObjectMapper mapper = new ObjectMapper();
        //mapper.getNodeFactory();
        JsonNode node = mapper.readTree(str);

        String name = node.get("name").toString();

        List<String> param = new ArrayList<>();
        param.add("123");
        try {
            String params = mapper.writeValueAsString(param);
            System.out.println(132);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(12);
    }



}
