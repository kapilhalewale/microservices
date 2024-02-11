package com.kp.customer.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kp.customer.dto.CustomerDto;
import com.kp.customer.dto.CustomerMessageDto;
import com.kp.customer.entity.Customer;
import com.kp.customer.entity.Notification;
import com.kp.customer.util.JsonUtil;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        if (Objects.nonNull(customerDto.getCustomerId())) {
            customer.setCustomerId(customerDto.getCustomerId());
        }
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Notification mapToNotification(CustomerMessageDto customerMessageDto, Notification notification) {
        String jsonMessage = JsonUtil.toJson(customerMessageDto);
        notification.setMessage(jsonMessage);
        return notification;
    }

    public static CustomerMessageDto mapToCustomerMessageDto(Customer customer, CustomerMessageDto customerMessageDto) {
        customerMessageDto.setCustomerId(customer.getCustomerId());
        customerMessageDto.setEmail(customer.getEmail());
        customerMessageDto.setMobile(customer.getMobileNumber());
        customerMessageDto.setName(customer.getName());
        customerMessageDto.setSource("Customer-Service");
        customerMessageDto.setTimestamp(LocalDateTime.now());
        return customerMessageDto;
    }
}