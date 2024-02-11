package com.kp.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Customer name cannot be null or empty")
    @Size(min = 2, max = 50, message = "Length of the name should be between 2 & 50")
    private String name;

    @NotEmpty(message = "Email address cannot be null or empty")
    @Size(min = 2, max = 50, message = "length of the email should be between 2 & 50")
    @Email(message = "Please enter valid email address")
    private String email;

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @JsonIgnore
    private long customerId;
}