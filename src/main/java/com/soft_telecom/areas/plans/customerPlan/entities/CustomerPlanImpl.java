package com.soft_telecom.areas.plans.customerPlan.entities;

import com.soft_telecom.areas.plans.customerPlan.entities.interfaces.CustomerPlan;
import com.soft_telecom.areas.users.entities.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer_plans")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class CustomerPlanImpl implements CustomerPlan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(name = "price_per_month")
    private double pricePerMonth;

    @Column
    private int bonuses;

    @Column(insertable = false, updatable = false)
    private String type;

    @Column(name = "plan_logo")
    private String planLogo;

    protected CustomerPlanImpl() {

    }

    public void setId(long newId){
        this.id = newId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPricePerMonth() {
        return this.pricePerMonth;
    }

    @Override
    public int getBonuses() {
        return this.bonuses;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlanLogo() {
        return planLogo;
    }

    public void setPlanLogo(String planLogo) {
        this.planLogo = planLogo;
    }
}
