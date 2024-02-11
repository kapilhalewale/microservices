package com.kp.customer.service.client;

import com.kp.customer.dto.client.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductFallback implements ProductFeignClient{
    @Override
    public ResponseEntity<ProductDto> getProductDetails(String correlationId, long productId) {
        return null;
    }
}