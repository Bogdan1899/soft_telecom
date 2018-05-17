package com.soft_telecom.areas.plans.mobilePlans.servicesImpl;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.mobilePlans.entities.MobilePlan;
import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.areas.plans.mobilePlans.services.MobilePlanService;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.AddMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.EditMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.viewModels.MobilePlanView;
import com.soft_telecom.areas.plans.customerPlan.repositories.PlanRepository;
import com.soft_telecom.areas.users.entities.User;
import com.soft_telecom.areas.users.repositories.UserRepository;
import com.soft_telecom.areas.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobilePlanServiceImpl implements MobilePlanService {

    private static final String IMAGE_PATH = "../bootstrap/img/";

    private PlanRepository planRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public MobilePlanServiceImpl(PlanRepository planRepository, ModelMapper modelMapper,  UserService userService) {
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addNewMobilePlan(AddMobilePlanModel addMobilePlanModel) {
        MobilePlan mobilePlan = this.modelMapper.map(addMobilePlanModel, MobilePlan.class);
        mobilePlan.setPlanLogo(IMAGE_PATH + addMobilePlanModel.getPlanLogo());
        this.planRepository.save(mobilePlan);
    }

    @Override
    public List<MobilePlanView> getAllMobilePlans(String type) {
        List<CustomerPlanImpl> mobilePlans = this.planRepository.findAllByType(type);
        List<MobilePlanView> views = new ArrayList<>();

        for (CustomerPlanImpl mobilePlan : mobilePlans) {
            MobilePlanView view = this.modelMapper.map(mobilePlan, MobilePlanView.class);
            views.add(view);
        }

        return views;
    }

    @Override
    public void editMobilePlan(EditMobilePlanModel editMobilePlanModel) {
        CustomerPlanImpl oldPlan = this.planRepository.findOne(editMobilePlanModel.getId());
        MobilePlan mobilePlan = this.modelMapper.map(editMobilePlanModel, MobilePlan.class);
         mobilePlan.setName(editMobilePlanModel.getName());
         mobilePlan.setBonuses(editMobilePlanModel.getBonuses());
         mobilePlan.setPlanLogo(oldPlan.getPlanLogo());
         this.planRepository.save(mobilePlan);
    }

    @Override
    public EditMobilePlanModel findEditMobilePlanById(long id) {
        CustomerPlanImpl mobilePlan = this.planRepository.findOne(id);
        EditMobilePlanModel editMobilePlanModel = this.modelMapper.map(mobilePlan, EditMobilePlanModel.class);
        return editMobilePlanModel;
    }

    @Override
    public void deleteMobilePlan(long id) {
        this.planRepository.delete(id);
    }

    @Override
    public void getMobilePlan(long id, String username) throws LackOfMoneyException {
        CustomerPlanImpl plan = this.planRepository.findOne(id);
        MobilePlan mobilePlan = this.modelMapper.map(plan, MobilePlan.class);

        this.userService.getPlan(mobilePlan, username);
    }
}
