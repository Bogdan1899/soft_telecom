package com.soft_telecom.areas.users.entities.interfaces;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.entities.Role;

import java.util.Date;

public interface Consumer {

    long getId();

    String getFirstName();

    String getLastName();

    String getPassword();

    double getMoney();

    void addRole(Role role);

    void addPlan(CustomerPlanImpl plan);

    void removePlan(CustomerPlanImpl plan);
}
