package com.example.springlv5.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("M"), FEMALE("F");

    private final String value;

    // body로 받은 enum validation 하는 경우
    @JsonCreator
    public static Gender parsing(String inputValue) {

        return Stream.of(Gender.values())
            .filter(gender -> gender.getValue().equals(inputValue.toUpperCase()))
            .findFirst()
            .orElse(null);
    }

}
