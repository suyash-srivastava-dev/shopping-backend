package com.suyash.shopping.controller;

import com.suyash.shopping.dto.RegisterRequest;
import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Role;
import com.suyash.shopping.repository.CustomerRepository;
import com.suyash.shopping.repository.RoleRepository;
import com.suyash.shopping.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/api")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/user/signup")
    public ResponseEntity<Customer> createEmployee(@RequestBody RegisterRequest registerRequest)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/api/user/signup").toUriString());
        return ResponseEntity.created(uri).body(customerService.saveCustomer(registerRequest));
    }
    @PostMapping("/role/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/api/role/create").toUriString());
        return ResponseEntity.created(uri).body(customerService.saveRole(role));
    }
}
