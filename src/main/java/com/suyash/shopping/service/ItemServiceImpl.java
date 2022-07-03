package com.suyash.shopping.service;

import com.suyash.shopping.dto.ItemCreateRequest;
import com.suyash.shopping.dto.StandardResponse;
import com.suyash.shopping.model.Item;
import com.suyash.shopping.repository.ItemRepository;
import com.suyash.shopping.repository.OutletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OutletRepository outletRepository;

    @Override
    public StandardResponse createItem(ItemCreateRequest itemCreate) {
        Item itemMap=itemRepository.findByName(itemCreate.getName())!=null?itemRepository.findByName(itemCreate.getName()):new Item();
        itemMap.setName(itemCreate.getName());
        itemMap.setAvailableQuantity(itemCreate.getAvailableQuantity());
        itemMap.setDiscountedSp(itemCreate.getDiscountedSellingPrice());
        itemMap.setDiscountPercent(itemCreate.getDiscountPercent());
        itemMap.setMrp(itemCreate.getMrp());
        itemMap.setWeightInGrams(itemCreate.getWeightInGms());
        itemMap.setOutOfStock(itemCreate.getOutOfStock().equalsIgnoreCase("true"));
        itemMap.setQuantity(itemCreate.getQuantity());

//        Inventory inventory=new Inventory();
//        inventory.setItem(itemMap);
//        Collection<Inventory> inventoryList=new ArrayList<>();
        StandardResponse standardResponse=new StandardResponse();
        itemMap.setInventory(null);
        itemRepository.save(itemMap);

        standardResponse.setCode(200);
        standardResponse.setMessage("Item created");
        return standardResponse;
    }

    @Override
    public StandardResponse createFromListOfItems(List<ItemCreateRequest> itemCreate) {

        itemCreate.forEach(item->{
            Item itemMap=itemRepository.findByName(item.getName())!=null?itemRepository.findByName(item.getName()):new Item();
            itemMap.setName(item.getName());
            itemMap.setAvailableQuantity(item.getAvailableQuantity());
            itemMap.setDiscountedSp(item.getDiscountedSellingPrice());
            itemMap.setDiscountPercent(item.getDiscountPercent());
            itemMap.setMrp(item.getMrp());
            itemMap.setWeightInGrams(item.getWeightInGms());
            itemMap.setOutOfStock(item.getOutOfStock().equalsIgnoreCase("true"));
            itemMap.setQuantity(item.getQuantity());
            itemMap.setInventory(null);
            itemRepository.save(itemMap);
        });

        StandardResponse standardResponse=new StandardResponse();
        standardResponse.setCode(200);
        standardResponse.setMessage("Items created");
        return standardResponse;
    }
}
