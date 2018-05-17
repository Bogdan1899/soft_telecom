package com.soft_telecom.areas.plans.mobilePlans.controllers;

import com.soft_telecom.areas.plans.mobilePlans.entities.MobilePlan;
import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.enums.PlanType;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.AddMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.EditMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.viewModels.MobilePlanView;
import com.soft_telecom.areas.plans.mobilePlans.services.MobilePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("mobile")
public class MobilePlanController {

    @Autowired
    private MobilePlanService planService;

    @GetMapping("")
    public String getMobilePlanPage(Model model){
        List<MobilePlanView> mobilePlanViews = this.planService.getAllMobilePlans(PlanType.MOBILE.name());
        model.addAttribute("mobilePlans", mobilePlanViews);

        return "mobile/mobile-all";
    }

    @GetMapping("add")
    public String getMobPlansAddPage(@ModelAttribute AddMobilePlanModel addMobilePlanModel){

        return "mobile/mobile-add";
    }

    @PostMapping("add")
    public String addMobilePlan(@Valid @ModelAttribute AddMobilePlanModel addMobilePlanModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addMobilePlanModel", addMobilePlanModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addMobilePlanModel", bindingResult);
            return "redirect:/mobile/add";
        }

        this.planService.addNewMobilePlan(addMobilePlanModel);

        return "redirect:/mobile";
    }

    @GetMapping("edit/{id}")
    public String getEditMobilePlanPage(@PathVariable long id, Model model){
        if (!model.containsAttribute("editMobilePlanModel")){
            EditMobilePlanModel editMobilePlanModel = this.planService.findEditMobilePlanById(id);
            model.addAttribute("editMobilePlanModel", editMobilePlanModel);
        }

        return "mobile/mobile-edit";
    }

    @PostMapping("edit/{id}")
    public String editMobilePlan(@PathVariable long id, @Valid @ModelAttribute EditMobilePlanModel editMobilePlanModel,  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editMobilePlanModel", editMobilePlanModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editMobilePlanModel", bindingResult);
            return "redirect:/mobile/edit/{id}";
        }

        editMobilePlanModel.setId(id);
        this.planService.editMobilePlan(editMobilePlanModel);

        return "redirect:/mobile";
    }

    @GetMapping("get/{id}")
    public String getPlan(@PathVariable long id, Principal principal) throws LackOfMoneyException {
        String username = principal.getName();
        this.planService.getMobilePlan(id, username);

        return "redirect:/mobile";
    }

    @GetMapping("delete/{id}")
    public String getDeleteMobilePlanPage(@PathVariable long id, Model model){
        EditMobilePlanModel editMobilePlanModel = this.planService.findEditMobilePlanById(id);
        model.addAttribute("deletePlan", editMobilePlanModel);

        return "mobile/mobile-delete";
    }

    @PostMapping("delete/{id}")
    public String deleteMobilePlan(@PathVariable long id){
        this.planService.deleteMobilePlan(id);

        return "redirect:/mobile";
    }

    @ExceptionHandler(LackOfMoneyException.class)
    public String catchLackOfMoneyException() {
        return "lack-of-money";
    }
}
