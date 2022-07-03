package com.suyash.shopping.controller;

import com.suyash.shopping.dto.*;
import com.suyash.shopping.service.CustomerService;
import com.suyash.shopping.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/v1/api/location")
@AllArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/distance")
    public ResponseEntity<List<NearestShopResponse>> createPost(@RequestBody NearestShopRequest nearestShopRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.nearestDistance(nearestShopRequest));
    }
    @PostMapping("/outlet/item-list")
    public ResponseEntity<List<ItemListResponse>> getItemsFromOutlet(@RequestBody OutletItemListRequest nameOfOutlet) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.getItemsFromOutlet(nameOfOutlet.getOutletName()));
    }
    @GetMapping("/outlet/buy/item/{itemId}/store/{outletId}")
    public ResponseEntity<List<ItemListResponse>> buyItemFromStore(@PathVariable Long itemId,@PathVariable Long outletId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.getUpdatedItemList(itemId,outletId));
    }
    @PostMapping("/outlet/buy/item-list")
    public ResponseEntity<List<ItemListResponse>> buyItemsFromOutlet(@RequestBody BuyItemsFromOutletRequest buyRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.buyItemsFromOutlet(buyRequest.getNameOfOutlet(),buyRequest.getListOfItemId()));
    }
}
