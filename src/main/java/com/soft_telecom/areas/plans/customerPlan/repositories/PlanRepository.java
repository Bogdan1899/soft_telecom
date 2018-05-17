package com.soft_telecom.areas.plans.customerPlan.repositories;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<CustomerPlanImpl, Long> {

    List<CustomerPlanImpl> findAllByType(String type);

}