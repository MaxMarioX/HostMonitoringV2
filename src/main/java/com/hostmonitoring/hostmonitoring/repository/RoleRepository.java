package com.hostmonitoring.hostmonitoring.repository;

import com.hostmonitoring.hostmonitoring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
