package com.suyash.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearestShopResponse {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private double distance;
    private String storeName;
}
