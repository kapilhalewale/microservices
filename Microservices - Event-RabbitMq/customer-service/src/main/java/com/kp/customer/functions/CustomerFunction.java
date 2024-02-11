package com.kp.customer.functions;

import com.kp.customer.dto.CustomerDto;
import com.kp.customer.dto.CustomerMessageDto;
import com.kp.customer.mapper.CustomerMapper;
import com.kp.customer.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CustomerFunction {

    private static final Logger log = LoggerFactory.getLogger(CustomerFunction.class);

    /*
     * This function is to catch the response from the message queue which we sent
     * from the customer service create API
     */
    @Bean
    public Consumer<CustomerMessageDto> saveNotification(ICustomerService iCustomerService) {
        return customerMessageDto -> {
            log.info("Notification received " + customerMessageDto.toString());
            iCustomerService.saveNotification(customerMessageDto, "RECEIVED");
        };
    }
}
