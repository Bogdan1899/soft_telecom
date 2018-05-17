package com.soft_telecom.areas.plans.mobilePlans.models.viewModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class MobilePlanView {

    private long id;

    private String name;

    private double pricePerMonth;

    private int bonuses;

    private int freeMinutes;

    private int freeSMS;

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

    public int getFreeMinutes() {
        return freeMinutes;
    }

    public void setFreeMinutes(int freeMinutes) {
        this.freeMinutes = freeMinutes;
    }

    public int getFreeSMS() {
        return freeSMS;
    }

    public void setFreeSMS(int freeSMS) {
        this.freeSMS = freeSMS;
    }

    public String getPlanLogo() {
        return planLogo;
    }

    public void setPlanLogo(String planLogo) {
        this.planLogo = planLogo;
    }
}
