package com.soft_telecom.areas.users.controllers;

import com.soft_telecom.areas.users.models.bindingModels.UserRegistrationModel;
import com.soft_telecom.areas.users.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SecurityUserController.class)
@ActiveProfiles("test")
public class SecurityUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void registerUser_ShouldRegisterUser() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("firstName", "Aaa")
                        .param("lastName", "Aaa")
                        .param("money", "1000")
                        .param("phoneNumber", "0859666666")
                        .param("username", "test@abv.bg")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(model().hasNoErrors());

        ArgumentCaptor<UserRegistrationModel> captor = ArgumentCaptor.forClass(UserRegistrationModel.class);
        verify(userService).registerUser(captor.capture());
        UserRegistrationModel model = captor.getValue();
        assertEquals("test@abv.bg", model.getUsername());
    }

    @Test
    public void registerGivenInvalidPasswords_ShouldNotRegister() throws Exception {
        this.mockMvc
                .perform(post("/register")
                        .param("firstName", "Aaa")
                        .param("lastName", "Aaa")
                        .param("money", "1000")
                        .param("phoneNumber", "0859666666")
                        .param("username", "test@abv.bg")
                        .param("password", "123456")
                        .param("confirmPassword", "12345")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register"))
                .andExpect(redirectedUrl("/register"));

        verifyZeroInteractions(this.userService);
    }

    @Test
    public void registerInvalidFirstName_ShouldNotRegister() throws Exception {
        this.mockMvc
                .perform(post("/register")
                        .param("lastName", "Aaa")
                        .param("money", "1000")
                        .param("phoneNumber", "0859666666")
                        .param("username", "test@abv.bg")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register"))
                .andExpect(redirectedUrl("/register"));

        verifyZeroInteractions(this.userService);
    }

    @Test
    public void registerInvalidLastName_ShouldNotRegister() throws Exception {
        this.mockMvc
                .perform(post("/register")
                        .param("firstName", "Aaa")
                        .param("money", "1000")
                        .param("phoneNumber", "0859666666")
                        .param("username", "test@abv.bg")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register"))
                .andExpect(redirectedUrl("/register"));

        verifyZeroInteractions(this.userService);
    }

    @Test
    public void registerInvalidPhoneNumberName_ShouldNotRegister() throws Exception {
        this.mockMvc
                .perform(post("/register")
                        .param("firstName", "Aaa")
                        .param("lastName", "Aaa")
                        .param("money", "1000")
                        .param("phoneNumber", "0889666666")
                        .param("username", "test@abv.bg")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register"))
                .andExpect(redirectedUrl("/register"));

        verifyZeroInteractions(this.userService);
    }
}