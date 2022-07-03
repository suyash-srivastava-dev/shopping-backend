package com.suyash.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemListResponse {
    private Long itemId;
    private String name;
    private Long mrp;
    private Long discountPercent;
    private int availableQuantity;
    private Long discountedSp;
    private int weightInGrams;
    private boolean outOfStock;
    private int quantity;
}
