package com.caf.automation.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

public final class ConvertJSON {

    private ConvertJSON(){}

    public static <T> T responseToObject(Response response, String path, Class<T> clazz) {

        // Get the JSON response as a LinkedHashMap
        LinkedHashMap<String, Object> jsonResponse = response.jsonPath().getJsonObject(path);

        // Use ObjectMapper to convert the LinkedHashMap to the desired object type
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(jsonResponse, clazz);
    }

}
