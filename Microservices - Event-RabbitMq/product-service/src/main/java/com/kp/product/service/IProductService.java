package com.kp.product.service;

import com.kp.product.dto.ProductDto;

import java.util.List;

public interface IProductService {

    void createProduct(ProductDto customerDto);

    List<ProductDto> getProducts();

    ProductDto getProductDetails(long productId);
}
