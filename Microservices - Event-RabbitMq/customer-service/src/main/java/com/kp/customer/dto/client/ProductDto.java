package com.kp.customer.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private String category;

    @JsonIgnore
    private long productId;

}