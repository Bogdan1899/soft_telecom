package com.soft_telecom.areas.plans.customerPlan.services;


import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.customerPlan.viewModels.CustomerPlanImplView;

import java.util.List;

public interface PlanService {
    List<CustomerPlanImplView> getMyPlans(String username);

    void removePlan(long id, String username);

    CustomerPlanImplView getPlanView(long id);
}
