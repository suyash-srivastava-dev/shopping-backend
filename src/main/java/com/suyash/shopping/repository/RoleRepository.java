package com.suyash.shopping.repository;

import com.suyash.shopping.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String role);
}
