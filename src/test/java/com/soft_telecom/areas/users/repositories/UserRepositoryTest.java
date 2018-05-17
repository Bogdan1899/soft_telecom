package com.soft_telecom.areas.users.repositories;

import com.soft_telecom.areas.users.entities.User;
import com.soft_telecom.entities.Role;
import com.soft_telecom.enums.PlanType;
import com.soft_telecom.enums.Roles;
import com.soft_telecom.repositories.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    private static final String USERNAME = "aa@aa.aa";
    private static final String USER_ROLE = "ROLE_USER";
    private static final int NUMBER_OF_USERS = 1;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        User user = new User();
        user.setUsername(USERNAME);

        this.testEntityManager.persist(user);
    }


    @Test
    public void findOneByUsername_ShouldReturnOneUserWithSameUsername() throws Exception {
        User user = this.userRepository.findOneByUsername(USERNAME);
        assertEquals(USERNAME, user.getUsername());
    }
}