package com.soft_telecom.areas.plans.mobilePlans.services;


import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.AddMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.EditMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.viewModels.MobilePlanView;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface MobilePlanService {

    @PreAuthorize("hasRole('ADMIN')")
    void addNewMobilePlan(AddMobilePlanModel addMobilePlanModel);

    List<MobilePlanView> getAllMobilePlans(String type);

    @PreAuthorize("hasRole('ADMIN')")
    void editMobilePlan(EditMobilePlanModel editMobilePlanModel);

    @PreAuthorize("hasRole('ADMIN')")
    EditMobilePlanModel findEditMobilePlanById(long id);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteMobilePlan(long id);

    void getMobilePlan(long id, String username) throws LackOfMoneyException;
}
