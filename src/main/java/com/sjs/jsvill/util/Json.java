package com.sjs.jsvill.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
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

    public static void contentLog(Object obj) {
        try {
            log.info(1);
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            log.info(2);
            log.info(obj.getClass() + " :)  \n" + result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
