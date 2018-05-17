package com.soft_telecom.areas.users.models.bindingModels;


import com.soft_telecom.entities.Role;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

public class EditUserModel {

    private long id;

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

    private String phoneNumber;

    private int bonusPoints;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
