package com.suyash.shopping.repository;

import com.suyash.shopping.dto.ItemListResponse;
import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);

}
