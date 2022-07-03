package com.suyash.shopping.repository;

import com.suyash.shopping.model.Inventory;
import com.suyash.shopping.model.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
//    List<Inventory> findAllById(Long outletId);

    List<Inventory> findAllByOutlet(Outlet outletId);

    Inventory findByOutlet(Outlet outletId);
//    @Query(value = "SELECT i FROM item i WHERE name='Lemon'",nativeQuery = true)
//    List<Item> getItemsFromOutlet(String outletName);

}
