package com.soft_telecom.areas.plans.tvInternetPlans.servicesImpl;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.customerPlan.entities.interfaces.CustomerPlan;
import com.soft_telecom.areas.plans.customerPlan.repositories.PlanRepository;
import com.soft_telecom.areas.plans.customerPlan.services.PlanService;
import com.soft_telecom.areas.plans.mobilePlans.entities.MobilePlan;
import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.areas.plans.tvInternetPlans.entities.TVAndInternetPlan;
import com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels.AddTvIntPlanModel;
import com.soft_telecom.areas.plans.tvInternetPlans.models.bindingModels.EditTvIntPlanModel;
import com.soft_telecom.areas.plans.tvInternetPlans.models.viewModels.TvAndInternetPlanView;
import com.soft_telecom.areas.plans.tvInternetPlans.services.TvInternetPlanService;
import com.soft_telecom.areas.users.entities.User;
import com.soft_telecom.areas.users.repositories.UserRepository;
import com.soft_telecom.areas.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TvInternetPlanServiceImpl implements TvInternetPlanService {

    private static final String IMAGE_PATH = "../bootstrap/img/";

    private PlanRepository planRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public TvInternetPlanServiceImpl(PlanRepository planRepository, ModelMapper modelMapper, UserService userService) {
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addNewTvAndInternetPlan(AddTvIntPlanModel addTvIntPlanModel) {
        TVAndInternetPlan tvAndInternetPlan = this.modelMapper.map(addTvIntPlanModel, TVAndInternetPlan.class);
        tvAndInternetPlan.setPlanLogo(IMAGE_PATH + addTvIntPlanModel.getPlanLogo());
        this.planRepository.save(tvAndInternetPlan);
    }

    @Override
    public List<TvAndInternetPlanView> getAllTvAndInternetPlans(String type) {
        List<CustomerPlanImpl> mobilePlans = this.planRepository.findAllByType(type);
        List<TvAndInternetPlanView> views = new ArrayList<>();

        for (CustomerPlanImpl mobilePlan : mobilePlans) {
            TvAndInternetPlanView view = this.modelMapper.map(mobilePlan, TvAndInternetPlanView.class);
            views.add(view);
        }

        return views;
    }

    @Override
    public void editTvAndInternetPlan(EditTvIntPlanModel editTvIntPlanModel) {
        CustomerPlanImpl oldPlan = this.planRepository.findOne(editTvIntPlanModel.getId());
        TVAndInternetPlan tvAndInternetPlan = this.modelMapper.map(editTvIntPlanModel, TVAndInternetPlan.class);
        tvAndInternetPlan.setName(editTvIntPlanModel.getName());
        tvAndInternetPlan.setBonuses(editTvIntPlanModel.getBonuses());
        tvAndInternetPlan.setPlanLogo(oldPlan.getPlanLogo());
        this.planRepository.save(tvAndInternetPlan);
    }

    @Override
    public EditTvIntPlanModel findTvAndInternetPlanById(long id) {
        CustomerPlanImpl mobilePlan = this.planRepository.findOne(id);
        EditTvIntPlanModel editTvIntPlanModel = this.modelMapper.map(mobilePlan, EditTvIntPlanModel.class);
        return editTvIntPlanModel;
    }

    @Override
    public void deleteTvInternetPlan(long id) {
        this.planRepository.delete(id);
    }

    @Override
    public void getTvInternetPlan(long id, String username) throws LackOfMoneyException {
        CustomerPlanImpl plan = this.planRepository.findOne(id);
        TVAndInternetPlan tvAndInternetPlan = this.modelMapper.map(plan, TVAndInternetPlan.class);

        this.userService.getPlan(tvAndInternetPlan, username);
    }
}
