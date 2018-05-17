package com.soft_telecom.areas.users.services;


import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.areas.plans.tvInternetPlans.entities.TVAndInternetPlan;
import com.soft_telecom.areas.users.entities.User;
import com.soft_telecom.areas.users.models.bindingModels.EditUserModel;
import com.soft_telecom.areas.users.models.bindingModels.UserRegistrationModel;
import com.soft_telecom.areas.users.models.viewModels.UserViewModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    void registerUser(UserRegistrationModel userRegistrationModel);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteUser(long id);

    EditUserModel getUserByUsername(String username);

    void editUser(EditUserModel editUserModel);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserViewModel> getAllUsers();

    EditUserModel getUserById(long id);

    void getPlan(CustomerPlanImpl customerPlan, String username) throws LackOfMoneyException;
}
