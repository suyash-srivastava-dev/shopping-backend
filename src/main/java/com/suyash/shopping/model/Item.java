package com.suyash.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long itemId;
    private String name;//
    private Long mrp;//decimal
    private Long discountPercent;//decimal
    private int availableQuantity;//
    private Long discountedSp;
    private int weightInGrams;
    private boolean outOfStock;
    private int quantity;
    @OneToMany(mappedBy = "item")
    private Collection<Inventory> inventory;
}
