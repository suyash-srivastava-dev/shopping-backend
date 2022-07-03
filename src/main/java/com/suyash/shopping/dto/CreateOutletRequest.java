package com.suyash.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOutletRequest {
    private String storeName;
    private String area;
    private String pincode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
