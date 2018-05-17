package com.soft_telecom.areas.plans.tvInternetPlans.controllers;


import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.areas.plans.tvInternetPlans.services.TvInternetPlanService;
import com.soft_telecom.enums.PlanType;
import com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels.AddTvIntPlanModel;
import com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels.EditTvIntPlanModel;
import com.soft_telecom.areas.plans.tvInternetPlans.models.viewModels.TvAndInternetPlanView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("tvinternet")
public class TvAndInternetPlanController {

    @Autowired
    private TvInternetPlanService planService;

    @GetMapping("")
    public String getTvInternetPlanPage(Model model){
        List<TvAndInternetPlanView> tvAndInternetPlans = this.planService.getAllTvAndInternetPlans(PlanType.TV_INTERNET.name());
        model.addAttribute("tvInternetPlans", tvAndInternetPlans);

        return "tv_internet/tv-internet-all";
    }

    @GetMapping("add")
    public String getTvInternetPlansPage(@ModelAttribute AddTvIntPlanModel addTvIntPlanModel){

        return "tv_internet/tv-internet-add";
    }

    @PostMapping("add")
    public String addTvAndInternetPlan(@Valid @ModelAttribute AddTvIntPlanModel addTvIntPlanModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addTvIntPlanModel", addTvIntPlanModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editTvAndIntPlanModel", bindingResult);
            return "redirect:/tvinternet/add";
        }

        this.planService.addNewTvAndInternetPlan(addTvIntPlanModel);

        return "redirect:/tvinternet";
    }

    @GetMapping("edit/{id}")
    public String getEditTvAndInternetPlanPage(@PathVariable long id, Model model){
        if (!model.containsAttribute("editTvAndIntPlanModel")){
            EditTvIntPlanModel editTvIntPlanModel = this.planService.findTvAndInternetPlanById(id);
            model.addAttribute("editTvAndIntPlanModel", editTvIntPlanModel);
        }

        return "tv_internet/tv-internet-edit";
    }

    @PostMapping("edit/{id}")
    public String editTvAndInternetPlan(@PathVariable long id, @Valid @ModelAttribute EditTvIntPlanModel editTvIntPlanModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editTvAndIntPlanModel", editTvIntPlanModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editTvAndIntPlanModel", bindingResult);
            return "redirect:/tvinternet/edit/{id}";
        }

        editTvIntPlanModel.setId(id);
        this.planService.editTvAndInternetPlan(editTvIntPlanModel);

        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String getDeleteTvIntPlanPage(@PathVariable long id, Model model){
        EditTvIntPlanModel editTvIntPlanModel = this.planService.findTvAndInternetPlanById(id);
        model.addAttribute("deletePlan", editTvIntPlanModel);

        return "tv_internet/tv-internet-delete";
    }

    @PostMapping("delete/{id}")
    public String deleteTvINtPlan(@PathVariable long id){
        this.planService.deleteTvInternetPlan(id);

        return "redirect:/";
    }

    @GetMapping("get/{id}")
    public String getPlan(@PathVariable long id, Principal principal) throws LackOfMoneyException {
        String username = principal.getName();
        this.planService.getTvInternetPlan(id, username);

        return "redirect:/";
    }

    @ExceptionHandler(LackOfMoneyException.class)
    public String catchLackOfMoneyException() {
        return "lack-of-money";
    }
}
