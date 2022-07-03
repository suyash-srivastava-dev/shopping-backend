package com.suyash.shopping.service;

import com.suyash.shopping.dto.CreateOutletRequest;
import com.suyash.shopping.dto.StandardResponse;

import java.util.List;

public interface OutletService {
    StandardResponse createOutlet(CreateOutletRequest itemCreate);

    StandardResponse createAllOutlet(List<CreateOutletRequest> bangloreOutletDetails);
}
