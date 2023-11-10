package com.example.springlv5.domain.product.converter;

import com.example.springlv5.domain.product.SortBy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSortBy implements Converter<String, SortBy> {

    @Override
    public SortBy convert(String source) {
        return SortBy.valueOf(source.toUpperCase());
    }
}
