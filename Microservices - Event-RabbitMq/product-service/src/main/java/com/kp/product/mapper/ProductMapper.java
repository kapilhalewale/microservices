package com.kp.product.mapper;

import com.kp.product.dto.ProductDto;
import com.kp.product.entity.Product;

public class ProductMapper {
    public static Product mapToProduct(ProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setStatus(Boolean.TRUE);
        return product;
    }

    public static ProductDto mapToProductDto(Product product, ProductDto productDto) {
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setProductId(product.getId());
        return productDto;
    }
}