package com.soft_telecom.areas.plans.customerPlan.servicesImpl;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.customerPlan.repositories.PlanRepository;
import com.soft_telecom.areas.plans.customerPlan.services.PlanService;
import com.soft_telecom.areas.plans.customerPlan.viewModels.CustomerPlanImplView;
import com.soft_telecom.areas.users.entities.User;
import com.soft_telecom.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{

    private UserRepository userRepository;

    private PlanRepository planRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PlanServiceImpl(UserRepository userRepository, PlanRepository planRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CustomerPlanImplView> getMyPlans(String username) {
        User user = this.userRepository.findOneByUsername(username);
        List<CustomerPlanImpl> plans = user.getPlans();
        List<CustomerPlanImplView> views = new ArrayList<>();

        for (CustomerPlanImpl plan : plans) {
            CustomerPlanImplView view = this.modelMapper.map(plan, CustomerPlanImplView.class);
            views.add(view);
        }

        return views;
    }

    @Override
    public void removePlan(long id, String username) {
        User user = this.userRepository.findOneByUsername(username);
        CustomerPlanImpl plan = this.planRepository.findOne(id);
        user.removePlan(plan);
        this.userRepository.save(user);
    }

    @Override
    public CustomerPlanImplView getPlanView(long id) {
        CustomerPlanImpl customerPlan = this.planRepository.findOne(id);
        CustomerPlanImplView view = this.modelMapper.map(customerPlan, CustomerPlanImplView.class);
        return view;
    }
}
