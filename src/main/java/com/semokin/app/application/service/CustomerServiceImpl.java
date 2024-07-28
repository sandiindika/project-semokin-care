package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.request.CustomerCreateRequest;
import com.semokin.app.adapter.dto.request.CustomerUpdateRequest;
import com.semokin.app.adapter.dto.response.CustomerResponse;
import com.semokin.app.application.contract.CustomerService;
import com.semokin.app.application.contract.ValidateService;
import com.semokin.app.domain.model.Customer;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidateService validateService;
    @Override
    @Transactional
    public CustomerResponse createCustomer(User user, CustomerCreateRequest request) {
        validateService.validate(request);
        // Implement business logic here to create a customer response from the given request
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setCreatedAt(new Date().getTime());
        customer.setUser(user);
        customerRepository.save(customer);

        return createResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse getCustomerById(User user, String id) {
        Customer customer = customerRepository.findFirstByUserAndId(user,id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,"Customer not found"));
        return createResponse(customer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(User user, String id) {
        Customer customer = customerRepository.findFirstByUserAndId(user,id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,"Customer not found"));
        customerRepository.delete(customer);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(User user, CustomerUpdateRequest request) {
        validateService.validate(request);
        Customer customer = customerRepository.findFirstByUserAndId(user,request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,"Customer not found"));

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setUpdatedAt(new Date().getTime());
        customerRepository.save(customer);
        return createResponse(customer);
    }

    @Override
    @Transactional
    public List<CustomerResponse> getAllCustomer() {
        return List.of();
    }

    private CustomerResponse createResponse(Customer customer) {
        return CustomerResponse.builder()
               .id(customer.getId())
               .firstName(customer.getFirstName())
               .lastName(customer.getLastName())
               .phoneNumber(customer.getPhoneNumber())
               .dateOfBirth(customer.getDateOfBirth())
               .loyaltyPoint(customer.getLoyaltyPoint())
               .build();
    }
}
