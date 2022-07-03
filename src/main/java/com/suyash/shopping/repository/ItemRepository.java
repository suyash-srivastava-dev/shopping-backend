package com.suyash.shopping.repository;

import com.suyash.shopping.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByName(String name);

    Item save(Item item);

   Item findAllByItemId(Long itemId);

    Item findByItemId(Long itemId);
}
