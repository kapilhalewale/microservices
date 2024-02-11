package com.kp.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

    @NotEmpty(message = "Product name cannot be null or empty")
    @Size(min = 2, max = 50, message = "Length of the name should be between 2 & 50")
    private String name;

    @NotEmpty(message = "Product category cannot be null or empty")
    @Size(min = 2, max = 50, message = "length of the email should be between 2 & 50")
    private String category;

    @JsonIgnore
    private long productId;

}