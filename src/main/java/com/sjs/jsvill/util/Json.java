package com.sjs.jsvill.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.reflections.Reflections.log;

public class Json {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void stringToJson(Object obj, String where) {
        try {
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            log.info(where + " :)  \n" + result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
