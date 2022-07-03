package com.suyash.shopping.service;

import com.suyash.shopping.dto.ItemCreateRequest;
import com.suyash.shopping.dto.StandardResponse;

import java.util.List;

public interface ItemService {

    StandardResponse createItem(ItemCreateRequest itemCreate);
    StandardResponse createFromListOfItems(List<ItemCreateRequest> itemCreate);

}
