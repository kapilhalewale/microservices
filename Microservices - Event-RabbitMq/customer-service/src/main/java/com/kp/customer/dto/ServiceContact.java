package com.kp.customer.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "contacts")
@Getter
@Setter
public class ServiceContact {

    private String message;
    private Map<String, String> contactDetails;
}
