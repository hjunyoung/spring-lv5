package com.example.springlv5.domain.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ProductRequest {
    @NotBlank(message = "상품명을 입력하세요")
    @Length(max = 255, message= "입력할 수 있는 글자수를 초과했습니다. (상품명)")
    private String name;

    @NotNull(message = "가격을 입력하세요")
    @Min(value = 100, message = "가격은 100 이상이어야 합니다")
    private double price;

    @NotNull(message = "재고를 입력하세요")
    @Min(value = 0, message = "재고는 0 이상이어야 합니다")
    private int stock;

    @NotBlank(message = "카테고리를 입력하세요")
    @Length(max = 32, message = "입력할 수 있는 글자수를 초과했습니다. (카테고리)")
    private String category;

    private String description;
    private MultipartFile imageUrl;

}
