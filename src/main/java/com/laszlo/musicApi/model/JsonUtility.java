package com.laszlo.musicApi.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//TODO: Determine in which layer this utility class should exist.

public class JsonUtility {
    public static String SONGSPATH = "src/main/resources/songs.json";
    public static String ARTISTSPATH = "src/main/resources/artists.json";


    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }

    public static <A> A createPojo(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static String readFileAsString(String file)
    {
        String content = "";
        try{
            content = new String(Files.readAllBytes(Paths.get(file)));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
