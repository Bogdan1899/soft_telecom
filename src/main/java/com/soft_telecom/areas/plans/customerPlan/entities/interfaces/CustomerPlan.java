package com.soft_telecom.areas.plans.customerPlan.entities.interfaces;


public interface CustomerPlan {

    long getId();

    String getName();

    double getPricePerMonth();

    int getBonuses();

    String getType();

    String getPlanLogo();
}
