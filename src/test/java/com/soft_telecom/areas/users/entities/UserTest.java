package com.soft_telecom.areas.users.entities;

import com.soft_telecom.entities.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserTest {

    private static final String ROLE_USER = "ROLE_USER";

    private User user;

    @Mock
    private Role role;

    @Before
    public void setUp(){
        this.user = new User();
        Mockito.when(this.role.getAuthority()).thenReturn(ROLE_USER);
    }

    @Test
    public void addRoleWhenRegisterUser_ShouldReturnRoleUser() throws Exception {

        this.user.addRole(role);

        String actualRoleName = this.user.getAuthorities().iterator().next().getAuthority();

        assertEquals(ROLE_USER, actualRoleName);
    }

}