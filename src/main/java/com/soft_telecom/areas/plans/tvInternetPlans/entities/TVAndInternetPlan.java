package com.soft_telecom.areas.plans.tvInternetPlans.entities;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@DiscriminatorValue(value = "TV_Internet")
public class TVAndInternetPlan extends CustomerPlanImpl {

    @Min(1)
    @Column(name = "number_of_channels")
    private int numberOfChannels;

    @Min(1)
    @Column(name = "internet_speed")
    private int internetSpeed;

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
}
