package com.soft_telecom.areas.plans.customerPlan.controllers;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.customerPlan.services.PlanService;
import com.soft_telecom.areas.plans.customerPlan.viewModels.CustomerPlanImplView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/myplans")
    public String getMyPlans(Model model, Principal principal){
        String username = principal.getName();
        List<CustomerPlanImplView> myPlans = this.planService.getMyPlans(username);
        model.addAttribute("myPlans", myPlans);

        return "myplans";
    }

    @GetMapping("/myplans/remove/{id}")
    public String removePlan(@PathVariable long id, Model model){
        CustomerPlanImplView customerPlanImplView = this.planService.getPlanView(id);
        model.addAttribute("customerPlanView", customerPlanImplView);

        return "myplan-delete";
    }

    @PostMapping("/myplans/remove/{id}")
    public String removePlan(@PathVariable long id, Principal principal){
        this.planService.removePlan(id, principal.getName());

        return "redirect:/myplans";
    }
}
