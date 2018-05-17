package com.soft_telecom.repositories;

import com.soft_telecom.entities.Role;
import com.soft_telecom.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findOneByAuthority(String authority);
}
