package com.suyash.shopping.repository;

import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);

}
