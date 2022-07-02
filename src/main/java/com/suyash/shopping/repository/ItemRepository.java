package com.suyash.shopping.repository;

import com.suyash.shopping.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
