package com.suyash.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Outlet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long outletId;
    private String storeName;
    private String area;
    private String pincode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    @OneToMany(mappedBy = "outlet")
    private Set<Inventory> inventory;
}
