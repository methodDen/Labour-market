package com.github.daniyar.trademarket.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonUtils {
    private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public JacksonUtils() {
    }
}
