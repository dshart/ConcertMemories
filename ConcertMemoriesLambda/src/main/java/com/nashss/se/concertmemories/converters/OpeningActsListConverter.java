package com.nashss.se.concertmemories.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.nashss.se.concertmemories.exceptions.OpeningActsSerializationException;

import java.util.List;

/**
 // * Converts between Data models and the representation we want to return in the result.
 */

public class OpeningActsListConverter implements DynamoDBTypeConverter <String, List<String>>{
    private ObjectMapper objectMapper = new ObjectMapper();
    private JavaTimeModule javaTimeModule = new JavaTimeModule();


    @Override
    public String convert(List<String> openingActs) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.writeValueAsString(openingActs);
        } catch (JsonProcessingException e) {
            throw new OpeningActsSerializationException("Error serializing list of opening Acts", e);
        }
    }

    @Override
    public List<String> unconvert(String concertString) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(concertString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new OpeningActsSerializationException("Error deserializing list of opening acts", e);
        }
    }
}