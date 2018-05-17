package com.soft_telecom.areas.plans.tvInternetPlans.services;


import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.AddMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.EditMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.viewModels.MobilePlanView;
import com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels.AddTvIntPlanModel;
import com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels.EditTvIntPlanModel;
import com.soft_telecom.areas.plans.tvInternetPlans.models.viewModels.TvAndInternetPlanView;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TvInternetPlanService {

    @PreAuthorize("hasRole('ADMIN')")
    void addNewTvAndInternetPlan(AddTvIntPlanModel addTvIntPlanModel);

    List<TvAndInternetPlanView> getAllTvAndInternetPlans(String type);

    @PreAuthorize("hasRole('ADMIN')")
    void editTvAndInternetPlan(EditTvIntPlanModel editTvIntPlanModel);

    @PreAuthorize("hasRole('ADMIN')")
    EditTvIntPlanModel findTvAndInternetPlanById(long id);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteTvInternetPlan(long id);

    void getTvInternetPlan(long id, String username) throws LackOfMoneyException;
}
