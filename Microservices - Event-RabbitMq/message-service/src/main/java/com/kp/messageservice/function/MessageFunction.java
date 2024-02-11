package com.kp.messageservice.function;

import com.kp.messageservice.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Function;


@Configuration
public class MessageFunction {

    private static final Logger log = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<CustomerDto, CustomerDto> email() {
        return customerDto -> {
            log.info("Sending email from message service : {} " + customerDto.toString());
            return customerDto;
        };
    }

    @Bean
    public Function<CustomerDto, CustomerDto> sms() {
        return customerDto -> {
            customerDto.setSource("Message-Service");
            customerDto.setTimestamp(LocalDateTime.now());
            log.info("Sending sms from message service : {} " + customerDto.toString());
            return customerDto;
        };
    }
}