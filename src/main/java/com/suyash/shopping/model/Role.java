package com.suyash.shopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Id
    private String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
