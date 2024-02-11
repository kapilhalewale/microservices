package com.kp.product.controller;


import com.kp.product.dto.ProductDto;
import com.kp.product.dto.ResponseDto;
import com.kp.product.dto.ServiceContact;
import com.kp.product.service.IProductService;
import com.kp.product.util.ProductConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Tag(
        name = "Product API",
        description = "Includes main APIs like onboard customer, get customer & view all customers"

)
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final IProductService iProductService;
    private final ServiceContact serviceContact;

    public ProductController(IProductService iProductService, ServiceContact serviceContact) {
        this.iProductService = iProductService;
        this.serviceContact = serviceContact;
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseDto> createNewProduct(@Valid @RequestBody ProductDto productDto) {
        iProductService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstant.STATUS_201, ProductConstant.NEW_RECORD_CREATED));
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDto> getProductDetails(@RequestHeader("correlation-id") String correlationId, @RequestParam long productId) {
        logger.debug("ps:: correlationId found: {}", correlationId);
        ProductDto productDto = iProductService.getProductDetails(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping("/service-info")
    public ResponseEntity<ServiceContact> getServiceDetails() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(serviceContact);
    }
}