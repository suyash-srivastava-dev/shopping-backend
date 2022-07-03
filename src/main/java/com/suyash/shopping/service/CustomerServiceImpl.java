package com.suyash.shopping.service;

import com.suyash.shopping.dto.*;
import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Role;
import com.suyash.shopping.repository.CustomerRepository;
import com.suyash.shopping.repository.InventoryRepository;
import com.suyash.shopping.repository.OutletRepository;
import com.suyash.shopping.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements UserDetailsService,CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    private OutletRepository outletRepository;
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
    public List<NearestShopResponse> nearestDistance(NearestShopRequest nearestShopRequest) {
        GeoLocation geoLocation=new GeoLocation(nearestShopRequest.getCurrentLocation().getLatitude(), nearestShopRequest.getCurrentLocation().getLongitude());
        List<NearestShopResponse> listNearestShopResponses=new ArrayList<>();
        outletRepository.findAll().forEach(outlet -> {
            NearestShopResponse nearestShopResponse=new NearestShopResponse();
            GeoLocation geoLocationOfStore=new GeoLocation(outlet.getLatitude(),outlet.getLongitude());
            nearestShopResponse.setLatitude(outlet.getLatitude());
            nearestShopResponse.setLongitude(outlet.getLongitude());
            nearestShopResponse.setDistance(getDistance(geoLocation,geoLocationOfStore));
            nearestShopResponse.setStoreName(outlet.getStoreName());
            listNearestShopResponses.add(nearestShopResponse);
        });
        Collections.sort(listNearestShopResponses, Comparator.comparing(NearestShopResponse::getDistance));
        return listNearestShopResponses;
    }


    Double rad (double x) {
        double doubleVal;
        doubleVal = x* 0.0174;
        return doubleVal;
    };

    double getDistance(GeoLocation p1, GeoLocation p2) {
        double R = 6378137; // Earth’s mean radius in meter
        double dLat = rad(p2.getLatitude().doubleValue() - p1.getLatitude().doubleValue());
        double dLong = rad(p2.getLongitude().doubleValue() - p1.getLongitude().doubleValue());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(rad(p1.getLatitude().doubleValue())) * Math.cos(rad(p2.getLatitude().doubleValue())) *
                        Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d; // returns the distance in meter
    };

//    @Override
//    public void addRoleToEmployee(Customer customer, String roleName) {
//        log.info("Adding new user: {} to role:{}",customer.getUsername(),roleName);
////        Customer customer= customerRepository.findByUsername(customerName);
//        Role role=roleRepository.findByName(roleName);
//        customer.getRole().add(role);
//    }

}
