package com.kp.customer.service;

import com.kp.customer.dto.CustomerDto;
import com.kp.customer.dto.CustomerMessageDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);

    boolean updateCustomer(CustomerDto customerDto);
    List<CustomerDto> getCustomers();

    CustomerDto getCustomerDetails(long customerId);

    boolean deleteCustomerByMobileNumber(String mobileNumber);

    void saveNotification(CustomerMessageDto customerMessageDto, String status);


}