package com.kp.customer;

import com.kp.customer.dto.ServiceContact;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {ServiceContact.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Customer Service REST API DOC",
                description = "Customer API",
                version = "V0.1",
                contact = @Contact(
                        name = "Kapil Kumar",
                        email = "kapilhalewale@gmail.com"
                )

        )
)
@EnableFeignClients
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
