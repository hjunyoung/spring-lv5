package com.example.springlv5.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("success"), FAIL("fail");
    private final String status;
}
