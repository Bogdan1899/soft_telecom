package com.soft_telecom.areas.users.models.bindingModels;


import com.soft_telecom.validations.IsPasswordsMatching;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@IsPasswordsMatching
public class UserRegistrationModel {

    @NotNull
    private String username;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Invalid first name!")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Invalid last name!")
    private String lastName;

    @NotNull
    @Min(0)
    private double money;

    @NotNull
    private String password;

    private String confirmPassword;

    @NotNull
    @Pattern(regexp = "^085[7-9][0-9]{6}$", message = "Invalid phone number!")
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
