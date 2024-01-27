package com.example.domain.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CountNamePair {
    private final int count;
    private final String name;

    public CountNamePair(@JsonProperty("Count") int count, @JsonProperty("Name") String name) {
        this.count = count;
        this.name = name;
    }
}
