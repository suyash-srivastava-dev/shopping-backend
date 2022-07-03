package com.suyash.shopping.service;

import com.suyash.shopping.dto.ItemListResponse;

import java.util.List;

public interface InventoryService {
    List<ItemListResponse> getItemsFromOutlet(String outletName);
//    List<Item> availableItemInOutletTiffin();
    List<ItemListResponse> getUpdatedItemList(Long itemId,Long outletId);

    List<ItemListResponse> buyItemsFromOutlet(String nameOfOutlet, List<Long> listOfItemId);
}
