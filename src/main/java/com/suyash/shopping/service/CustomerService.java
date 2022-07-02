package com.suyash.shopping.service;

import com.suyash.shopping.dto.RegisterRequest;
import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Role;

public interface CustomerService {
    Customer saveCustomer(RegisterRequest registerRequest);

    Role saveRole(Role role);

    void addRoleToEmployee(Customer customer,String roleName);

}
