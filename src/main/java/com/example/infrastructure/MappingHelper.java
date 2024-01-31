package com.example.infrastructure;

import com.example.domain.helpers.CountNamePair;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MappingHelper {
    @Getter
    private final ObjectMapper mapper;

    public String from(List<CountNamePair> pairs) {
        try {
            return mapper.writeValueAsString(pairs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not map object to string. \n %s", e);
        }
    }

    public List<CountNamePair> jsonToList(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<CountNamePair>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not map string to object. \n %s", e);
        }
    }
}
