package com.kp.customer.controller;

import com.kp.customer.dto.CustomerDto;
import com.kp.customer.dto.ResponseDto;
import com.kp.customer.dto.ServiceContact;
import com.kp.customer.dto.client.ProductDto;
import com.kp.customer.service.ICustomerService;
import com.kp.customer.service.client.ProductFeignClient;
import com.kp.customer.util.CustomerConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Tag(
        name = "Customer API",
        description = "Includes main APIs like onboard customer, get customer & view all customers"

)
public class CustomerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerService iCustomerService;
    private final ProductFeignClient productFeignClient;

    @Autowired
    private ServiceContact serviceContact;

    public CustomerController(ICustomerService iCustomerService, ProductFeignClient productFeignClient) {
        this.iCustomerService = iCustomerService;
        this.productFeignClient = productFeignClient;
    }

    @PostMapping("/customer")
    public ResponseEntity<ResponseDto> onboardCustomer(@RequestHeader("correlation-id") String correlationId, @Valid @RequestBody CustomerDto customerDto) {
        logger.debug("correlationId found: {}", correlationId);
        iCustomerService.createCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CustomerConstant.STATUS_201, CustomerConstant.NEW_CUSTOMER_CREATED));
    }

    @PutMapping("/customer")
    public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        iCustomerService.updateCustomer(customerDto);
        Boolean updateStatus = iCustomerService.updateCustomer(customerDto);
        if (updateStatus) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstant.STATUS_200, CustomerConstant.UPDATE_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstant.STATUS_500, CustomerConstant.ERROR_MESSAGE));
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerDto> getCustomerDetails(@RequestHeader("correlation-id") String correlationId, @RequestParam long customerId) {
        logger.debug("ps: correlationId found: {}", correlationId);
        CustomerDto customerDto = iCustomerService.getCustomerDetails(customerId);
        ResponseEntity<ProductDto> productDetails = productFeignClient.getProductDetails(correlationId, 1);
        if(productDetails != null){
            System.out.println("productDetails" + productDetails.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam String mobileNumber) {
        Boolean deleteStatus = iCustomerService.deleteCustomerByMobileNumber(mobileNumber);
        if (deleteStatus) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstant.STATUS_200, CustomerConstant.DELETE_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstant.STATUS_500, CustomerConstant.ERROR_MESSAGE));
        }
    }

    @GetMapping("/service-info")
    public ResponseEntity<ServiceContact> getCustomerServiceDetails() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(serviceContact);
    }
}