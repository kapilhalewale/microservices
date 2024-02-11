package com.kp.customer.service.client;


import com.kp.customer.dto.client.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", fallback = ProductFallback.class)
public interface ProductFeignClient {

    @GetMapping(value = "/api/v1/product", consumes = "application/json")
    ResponseEntity<ProductDto> getProductDetails(@RequestHeader("correlation-id") String correlationId, @RequestParam long productId);

}