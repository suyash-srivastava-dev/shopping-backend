package com.suyash.shopping.repository;

import com.suyash.shopping.model.Inventory;
import com.suyash.shopping.model.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutletRepository extends JpaRepository<Outlet,Long> {

    @Query(value = "SELECT * from item item_s JOIN outlet outlet_s ON item_s.item_id=",nativeQuery = true)
    public List<Inventory> getDspecss(String specs);

    Outlet findByStoreName(String storeName);

    Outlet findByOutletId(Long outletId);
}
