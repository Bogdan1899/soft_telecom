package com.soft_telecom.areas.users.repositories;

import com.soft_telecom.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findOneByUsername(String username);

    @Query(value = "SELECT u FROM User AS u\n" +
            "JOIN u.authorities AS r " +
            "WHERE r.authority = 'ROLE_USER'")
    List<User> getAllWithRoleUser();
}
