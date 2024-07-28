package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.request.CustomerCreateRequest;
import com.semokin.app.adapter.dto.request.CustomerUpdateRequest;
import com.semokin.app.adapter.dto.response.CustomerResponse;
import com.semokin.app.domain.model.User;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(User user,CustomerCreateRequest request);
    CustomerResponse getCustomerById(User user,String id); // todo: GET /customers/{id}
    void deleteCustomerById(User user,String id); // todo: DEL /products/{id}
    CustomerResponse updateCustomer(User user, CustomerUpdateRequest request); // todo: PATCH /customers/{id}
    List<CustomerResponse> getAllCustomer(); // todo: GET /customers/


}
