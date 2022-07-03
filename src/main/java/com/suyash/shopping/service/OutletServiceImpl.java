package com.suyash.shopping.service;

import com.suyash.shopping.dto.CreateOutletRequest;
import com.suyash.shopping.dto.StandardResponse;
import com.suyash.shopping.model.Inventory;
import com.suyash.shopping.model.Outlet;
import com.suyash.shopping.repository.InventoryRepository;
import com.suyash.shopping.repository.ItemRepository;
import com.suyash.shopping.repository.OutletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OutletServiceImpl implements OutletService  {
    @Autowired
    private OutletRepository outletRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Override
    public StandardResponse createOutlet(CreateOutletRequest outletRequest) {
        Outlet outlet=outletRepository.findByStoreName(outletRequest.getStoreName())!=null?outletRepository.findByStoreName(outletRequest.getStoreName()):new Outlet();
        outlet.setArea(outletRequest.getArea());
        outlet.setLatitude(outletRequest.getLatitude());
        outlet.setLongitude(outletRequest.getLongitude());
        outlet.setPincode(outletRequest.getPincode().replaceAll(" ",""));
        outlet.setStoreName(outletRequest.getStoreName());
        outlet.setInventory(null);
        outletRepository.save(outlet);
        //Adding outlet with all the items on opening
        itemRepository.findAll().forEach(item->{
            Inventory inventory=new Inventory();
            inventory.setItem(item);
            inventory.setOutlet(outlet);
            inventory.setRemainingItem(item.getAvailableQuantity());
            inventoryRepository.save(inventory);
        });
        StandardResponse standardResponse=new StandardResponse();
        standardResponse.setCode(200);
        standardResponse.setMessage("Outlet created successfully");
        return standardResponse;
    }

    @Override
    public StandardResponse createAllOutlet(List<CreateOutletRequest> bangloreOutletDetails) {
        bangloreOutletDetails.forEach(outletRequest->{
        Outlet outlet=outletRepository.findByStoreName(outletRequest.getStoreName())!=null?outletRepository.findByStoreName(outletRequest.getStoreName()):new Outlet();
        outlet.setArea(outletRequest.getArea());
            outlet.setLatitude(outletRequest.getLatitude());
            outlet.setLongitude(outletRequest.getLongitude());
        outlet.setPincode(outletRequest.getPincode().replaceAll(" ",""));
        outlet.setStoreName(outletRequest.getStoreName());
        outlet.setInventory(null);
        outletRepository.save(outlet);
        //Adding outlet with all the items on opening
        itemRepository.findAll().forEach(item->{
            Inventory inventory=new Inventory();
            inventory.setItem(item);
            inventory.setOutlet(outlet);
            inventory.setRemainingItem(item.getAvailableQuantity());
            inventoryRepository.save(inventory);
        });
        });
        StandardResponse standardResponse=new StandardResponse();
        standardResponse.setCode(200);
        standardResponse.setMessage("Outlet created successfully");
        return standardResponse;
    }
}
