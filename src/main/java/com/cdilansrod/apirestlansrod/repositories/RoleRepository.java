package com.cdilansrod.apirestlansrod.repositories;

import java.util.Optional;
import com.cdilansrod.apirestlansrod.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cdilansrod.apirestlansrod.entities.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
