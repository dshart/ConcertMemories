package com.nashss.se.concertmemories.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nashss.se.concertmemories.exceptions.MemoriesSerializationException;

import java.util.List;

public class MemoriesListConverter implements DynamoDBTypeConverter <String, List<String>>{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JavaTimeModule javaTimeModule = new JavaTimeModule();


    @Override
    public String convert(List<String> memories) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.writeValueAsString(memories);
        } catch (JsonProcessingException e) {
            throw new MemoriesSerializationException("Error serializing concert memories list", e);
        }
    }

    @Override
    public List<String> unconvert(String memoriesString) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(memoriesString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new MemoriesSerializationException("Error deserializing concert memories list", e);
        }
    }
}