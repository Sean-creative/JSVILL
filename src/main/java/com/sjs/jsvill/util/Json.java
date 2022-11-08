package com.sjs.jsvill.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Json {

        static ObjectMapper objectMapper = new ObjectMapper();
        static  {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }

    public static void stringToJson(Object obj, String where) {
        try {
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            log.info(where + " :)  \n" + result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
