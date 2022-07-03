package com.suyash.shopping.service;

import com.suyash.shopping.dto.ItemListResponse;
import com.suyash.shopping.dto.NearestShopRequest;
import com.suyash.shopping.dto.NearestShopResponse;
import com.suyash.shopping.dto.RegisterRequest;
import com.suyash.shopping.model.Customer;
import com.suyash.shopping.model.Role;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(RegisterRequest registerRequest);

    Role saveRole(Role role);

    List<NearestShopResponse> nearestDistance(NearestShopRequest nearestShopRequest);

}
