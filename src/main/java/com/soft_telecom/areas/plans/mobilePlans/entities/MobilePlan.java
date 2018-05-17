package com.soft_telecom.areas.plans.mobilePlans.entities;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Mobile")
public class MobilePlan extends CustomerPlanImpl {

    @Column(name = "free_minutes")
    private int freeMinutes;

    @Column(name = "free_SMS")
    private int freeSMS;

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
}
