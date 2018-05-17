package com.soft_telecom.areas.plans.repositories;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.customerPlan.repositories.PlanRepository;
import com.soft_telecom.areas.plans.mobilePlans.entities.MobilePlan;
import com.soft_telecom.areas.plans.tvInternetPlans.entities.TVAndInternetPlan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PlanRepositoryTest {

    private static final String MOBILE_TYPE = "Mobile";
    private static final String TV_INTERNET_TYPE = "TV_Internet";
    private static final String MOBILE_PLAN_NAME = "Mock";
    private static final String TV_INTERNET_PLAN_NAME = "Mock 2";
    private static final int NUMBER_OF_PLANS = 1;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PlanRepository planRepository;

    @Before
    public void setUp(){
        MobilePlan mobilePlan = new MobilePlan();
        mobilePlan.setName(MOBILE_PLAN_NAME);

        TVAndInternetPlan tvAndInternetPlan = new TVAndInternetPlan();
        tvAndInternetPlan.setName(TV_INTERNET_PLAN_NAME);
        tvAndInternetPlan.setPlanLogo("sth.jpg");
        tvAndInternetPlan.setInternetSpeed(100);
        tvAndInternetPlan.setNumberOfChannels(100);
        tvAndInternetPlan.setBonuses(2);
        tvAndInternetPlan.setPricePerMonth(12);

        this.testEntityManager.persist(mobilePlan);
        this.testEntityManager.persist(tvAndInternetPlan);
    }

    @Test
    public void findAllByType_ShouldReturnOneMobile() throws Exception {
        List<CustomerPlanImpl> mobilePlans = this.planRepository.findAllByType(MOBILE_TYPE);

        assertEquals(NUMBER_OF_PLANS, mobilePlans.size());
    }

    @Test
    public void findAllByType_ShouldReturnOneMobileWithSpecificName() throws Exception {
        List<CustomerPlanImpl> mobilePlans = this.planRepository.findAllByType(MOBILE_TYPE);

        CustomerPlanImpl plan = mobilePlans.get(0);

        assertEquals(MOBILE_PLAN_NAME, plan.getName());
    }

    @Test
    public void findAllByType_ShouldReturnOneTvInternet() throws Exception {
        List<CustomerPlanImpl> plans = this.planRepository.findAllByType(TV_INTERNET_TYPE);

        assertEquals(NUMBER_OF_PLANS, plans.size());
    }

    @Test
    public void findAllByType_ShouldReturnOneTvInternetWithSpecificName() throws Exception {
        List<CustomerPlanImpl> plans = this.planRepository.findAllByType(TV_INTERNET_TYPE);

        CustomerPlanImpl plan = plans.get(0);

        assertEquals(TV_INTERNET_PLAN_NAME, plan.getName());
    }
}