package com.example.springlv5.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortBy {
    NAME("name"), PRICE("price");

    private final String value;

}
