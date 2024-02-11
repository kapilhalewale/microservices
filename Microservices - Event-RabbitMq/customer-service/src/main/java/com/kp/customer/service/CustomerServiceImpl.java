package com.kp.customer.service;

import com.kp.customer.dto.CustomerDto;
import com.kp.customer.dto.CustomerMessageDto;
import com.kp.customer.entity.Customer;
import com.kp.customer.entity.Notification;
import com.kp.customer.exception.CustomerAlreadyExistsException;
import com.kp.customer.exception.ResourceNotFoundException;
import com.kp.customer.mapper.CustomerMapper;
import com.kp.customer.repository.CustomerRepository;
import com.kp.customer.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.kp.customer.mapper.CustomerMapper.mapToCustomerMessageDto;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private CustomerRepository customerRepository;
    private NotificationRepository notificationRepository;
    private final StreamBridge streamBridge;

    @Override
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with given mobile number " + customerDto.getMobileNumber());
        }
        Customer persistedCustomer = customerRepository.save(customer);
        if (Objects.nonNull(persistedCustomer)) {
            triggerCommunication(persistedCustomer);
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Boolean status = Boolean.FALSE;
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional = customerRepository.findById(customerDto.getCustomerId());
        if (!customerOptional.isPresent()) {
            throw new ResourceNotFoundException("Customer", "CustomerId", String.valueOf(customerDto.getCustomerId()));
        }
        status = Boolean.TRUE;
        customerRepository.save(customer);
        return status;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return null;
    }

    @Override
    public CustomerDto getCustomerDetails(long customerId) {
        Optional<Customer> customerObj = Optional.ofNullable(customerRepository.
                findById(customerId).orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "customerId", String.valueOf(customerId))
                ));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customerObj.get(), new CustomerDto());
        return customerDto;
    }

    @Override
    public boolean deleteCustomerByMobileNumber(String mobileNumber) {
        Optional.ofNullable(customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", String.valueOf(mobileNumber))
        ));
        customerRepository.deleteByMobileNumber(mobileNumber);
        return Boolean.TRUE;
    }

    @Override
    public void saveNotification(CustomerMessageDto customerMessageDto, String status) {
        Notification notification = CustomerMapper.mapToNotification(customerMessageDto, new Notification());
        notification.setStatus(status);
        notificationRepository.save(notification);
    }


    private void triggerCommunication(Customer customer) {
        var customerMessageDto = mapToCustomerMessageDto(customer, new CustomerMessageDto());
        log.info("Sending communication request ", customerMessageDto);
        var status = streamBridge.send("sendCommunication-out-0", customerMessageDto);
        log.info("Communication request triggered  ", status);
        saveNotification(customerMessageDto, "SENT");
    }
}