package com.soft_telecom.areas.users.controllers;

import com.soft_telecom.areas.users.models.bindingModels.EditUserModel;
import com.soft_telecom.areas.users.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final long ID = 1;
    private static final String FIRST_NAME = "Aaaaa";
    private static final String LAST_NAME = "Bbbbb";
    private static final String NUMBER = "0857121212";
    private static final String USERNAME = "aaa@bbb.bg";
    private static final String EXPECTED_NAME = "Ccccc";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        EditUserModel user = new EditUserModel();
        user.setUsername(USERNAME);
        user.setPhoneNumber(NUMBER);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);

        Mockito.when(this.userService.getUserByUsername(USERNAME)).thenReturn(user);
    }

    @Test
    public void getAllUsersPage_ShouldGetPage() throws Exception {
            this.mockMvc
                    .perform(get("/users/all"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("users/users"));

    }

    @Test
    public void editUser_ShouldEditUser() throws Exception {
        this.mockMvc
                .perform(post("/edit")
                        .param("firstName", EXPECTED_NAME)
                        .param("lastName", LAST_NAME)
                        .param("username", USERNAME)
                        .param("money", "1000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/edit"))
                .andExpect(redirectedUrl("/edit"));

        ArgumentCaptor<EditUserModel> captor = ArgumentCaptor.forClass(EditUserModel.class);
        verify(userService).editUser(captor.capture());
        EditUserModel model = captor.getValue();
        assertEquals(EXPECTED_NAME, model.getFirstName());
    }

    @Test
    public void editUser_InvalidFirstName_ShouldFailToEditUser() throws Exception {
        this.mockMvc
                .perform(post("/edit")
                        .param("firstName", "")
                        .param("lastName", LAST_NAME)
                        .param("username", USERNAME)
                        .param("money", "1000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/edit"))
                .andExpect(redirectedUrl("/edit"));

        verifyZeroInteractions(this.userService);
    }

}