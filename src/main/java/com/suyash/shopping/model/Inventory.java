package com.suyash.shopping.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Inventory {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    Outlet outlet;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;


    int remainingItem;
}
