package com.suyash.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateRequest {
    private String name;
    private Long mrp;
    private Long discountPercent;
    private int availableQuantity;
    private Long discountedSellingPrice;
    private int weightInGms;
    private String outOfStock;
    private int quantity;
}
