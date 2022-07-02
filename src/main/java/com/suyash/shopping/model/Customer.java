package com.suyash.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long customerId;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role;

}
