package com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels;


import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditTvIntPlanModel {

    private long id;

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid name")
    private String name;

    @Min(1)
    private double pricePerMonth;

    @Min(value = 0, message = "The value must be positive!")
    private int bonuses;

    @Min(1)
    private int numberOfChannels;

    @Min(1)
    private int internetSpeed;

    private String planLogo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public int getNumberOfChannels() {
        return numberOfChannels;
    }

    public void setNumberOfChannels(int numberOfChannels) {
        this.numberOfChannels = numberOfChannels;
    }

    public int getInternetSpeed() {
        return internetSpeed;
    }

    public void setInternetSpeed(int internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    public String getPlanLogo() {
        return planLogo;
    }

    public void setPlanLogo(String planLogo) {
        this.planLogo = planLogo;
    }
}
