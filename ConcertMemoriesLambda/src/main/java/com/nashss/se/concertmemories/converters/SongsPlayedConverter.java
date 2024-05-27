package com.nashss.se.concertmemories.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.nashss.se.concertmemories.exceptions.OpeningActsSerializationException;
import com.nashss.se.concertmemories.exceptions.SongsPlayedSerializationException;

import java.util.List;

/**
 // * Converts between Data models and the representation we want to return in the result.
 */
public class SongsPlayedConverter implements DynamoDBTypeConverter <String, List<String>>{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JavaTimeModule javaTimeModule = new JavaTimeModule();


    @Override
    public String convert(List<String> songsPlayed) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.writeValueAsString(songsPlayed);
        } catch (JsonProcessingException e) {
            throw new SongsPlayedSerializationException("Error serializing concert songs played", e);
        }
    }

    @Override
    public List<String> unconvert(String songsPlayedString) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(songsPlayedString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new SongsPlayedSerializationException("Error deserializing concert songs played", e);
        }
    }
}
