package com.suyash.shopping.service;

import com.suyash.shopping.dto.RegisterRequest;
import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Role;
import com.suyash.shopping.repository.CustomerRepository;
import com.suyash.shopping.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements UserDetailsService,CustomerService {
    private final CustomerRepository customerRepository;

//    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user= customerRepository.findByUsername(username);
        if(user == null)
        {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in DB");
        }
        else{
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities =new ArrayList<>();
        user.getRole().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
        );
        return new User(user.getUsername(),user.getPassword(),authorities);
    }

    public Customer saveCustomer(RegisterRequest registerRequest) {
        log.info("Saving new user {}",registerRequest);
        Role role=roleRepository.findByName(registerRequest.getRole())!=null?roleRepository.findByName(registerRequest.getRole()):null;
        Customer customer = new Customer();
        //if user exists ?
        if(customerRepository.findByUsername(registerRequest.getUsername())!=null)
            throw new UsernameNotFoundException("User not found in DB");
//        role=roleRepository.findByName(registerRequest.getRole());
        customer.setEmail(registerRequest.getEmail());
        customer.setUsername(registerRequest.getUsername());
        customer.setPassword(passwordEncoder(registerRequest.getPassword()));
        Collection<Role> collectionOfRole=new ArrayList<>();
        collectionOfRole.add(role);
        customer.setRole(collectionOfRole);
        return customerRepository.save(customer);
    }

    public String passwordEncoder(String pass)
    {
        return new BCryptPasswordEncoder().encode(pass);
    }
    public Role saveRole(Role role) {
        log.info("Saving new role {}",role.getName());
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        role.setId(uuidAsString);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToEmployee(Customer customer, String roleName) {
        log.info("Adding new user: {} to role:{}",customer.getUsername(),roleName);
//        Customer customer= customerRepository.findByUsername(customerName);
        Role role=roleRepository.findByName(roleName);
        customer.getRole().add(role);
    }

}
