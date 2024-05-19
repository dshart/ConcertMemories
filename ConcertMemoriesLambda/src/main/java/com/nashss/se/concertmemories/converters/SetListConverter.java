package com.nashss.se.concertmemories.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.nashss.se.concertmemories.exceptions.OpeningActsSerializationException;
import com.nashss.se.concertmemories.exceptions.SetListSerializationException;

import java.util.List;

public class SetListConverter implements DynamoDBTypeConverter <String, List<String>>{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JavaTimeModule javaTimeModule = new JavaTimeModule();


    @Override
    public String convert(List<String> setList) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.writeValueAsString(setList);
        } catch (JsonProcessingException e) {
            throw new OpeningActsSerializationException("Error serializing concert set list", e);
        }
    }

    @Override
    public List<String> unconvert(String setListString) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(setListString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new SetListSerializationException("Error deserializing concert set list", e);
        }
    }
}
