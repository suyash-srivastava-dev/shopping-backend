package com.suyash.shopping.service;

import com.suyash.shopping.dto.ItemListResponse;
import com.suyash.shopping.model.Inventory;
import com.suyash.shopping.model.Item;
import com.suyash.shopping.model.Outlet;
import com.suyash.shopping.repository.InventoryRepository;
import com.suyash.shopping.repository.ItemRepository;
import com.suyash.shopping.repository.OutletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private OutletRepository outletRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public List<ItemListResponse> getItemsFromOutlet(String outletName) {
        Outlet outlet=outletRepository.findByStoreName(outletName);
        List<ItemListResponse> itemListResponses=new ArrayList<>();
        inventoryRepository.findAllByOutlet(outlet).forEach(
                inventory -> {
                    Item item= itemRepository.findAllByItemId(inventory.getItem().getItemId());
                    ItemListResponse itemListResponse=new ItemListResponse(
                            item.getItemId(),item.getName(),item.getMrp(),item.getDiscountPercent(),inventory.getRemainingItem(),item.getDiscountedSp(),item.getWeightInGrams(),item.isOutOfStock(),item.getQuantity());
                    itemListResponses.add(itemListResponse );
        });
        return itemListResponses;
//        return null;
    }

    @Override
    public List<ItemListResponse> getUpdatedItemList(Long itemId, Long outletId) {
        Outlet outlet=outletRepository.findByOutletId(outletId);
        List<ItemListResponse> itemListResponses=new ArrayList<>();
       inventoryRepository.findAllByOutlet(outlet).forEach(
               inventory -> {
                   if(Objects.equals(inventory.getItem().getItemId(), itemId)) {
                       Item item = itemRepository.findByItemId(itemId);
                       inventory.setRemainingItem(inventory.getRemainingItem() - 1);
                       inventoryRepository.save(inventory);
                       ItemListResponse itemListResponse = new ItemListResponse(
                               item.getItemId(), item.getName(), item.getMrp(), item.getDiscountPercent(), inventory.getRemainingItem(), item.getDiscountedSp(), item.getWeightInGrams(), item.isOutOfStock(), item.getQuantity());
                       itemListResponses.add(itemListResponse);
                   }
               }
       );



        return itemListResponses;
    }

    @Override
    public List<ItemListResponse> buyItemsFromOutlet(String nameOfOutlet, List<Long> listOfItemId) {
        Outlet outlet=outletRepository.findByStoreName(nameOfOutlet);
        List<ItemListResponse> itemListResponses=new ArrayList<>();
        inventoryRepository.findAllByOutlet(outlet).forEach(
                inventory -> {
                    listOfItemId.forEach(itemId->{
                        if(itemId.equals(inventory.getItem().getItemId()))
                        {
                            Item item = itemRepository.findByItemId(itemId);
                            inventory.setRemainingItem(inventory.getRemainingItem() - 1);
                            inventoryRepository.save(inventory);
                            ItemListResponse itemListResponse = new ItemListResponse(
                                    item.getItemId(), item.getName(), item.getMrp(), item.getDiscountPercent(), inventory.getRemainingItem(), item.getDiscountedSp(), item.getWeightInGrams(), item.isOutOfStock(), item.getQuantity());
                            itemListResponses.add(itemListResponse);
                        }

                    });

                });
        return itemListResponses;
    }

}
