package com.soft_telecom.areas.plans.controllers;

import com.soft_telecom.areas.plans.mobilePlans.controllers.MobilePlanController;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.AddMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.models.bindingModels.EditMobilePlanModel;
import com.soft_telecom.areas.plans.mobilePlans.services.MobilePlanService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MobilePlanController.class)
@ActiveProfiles("test")
public class MobilePlanControllerTest {

    private static final String MODEL_NAME = "First";
    private static final String PLAN_LOGO = "plan";
    private static final long MODEL_ID = 1;
    private static final int BONUSES = 3;
    private static final int EXPECTED_BONUS = 5;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MobilePlanService planService;

    @Before
    public void setUp() throws Exception {
        EditMobilePlanModel editMobilePlanModel = new EditMobilePlanModel();
        editMobilePlanModel.setId(MODEL_ID);
        editMobilePlanModel.setName(MODEL_NAME);
        editMobilePlanModel.setBonuses(BONUSES);
        editMobilePlanModel.setPlanLogo(PLAN_LOGO);
        editMobilePlanModel.setPricePerMonth(12);
        editMobilePlanModel.setFreeMinutes(12);
        editMobilePlanModel.setFreeSMS(12);

        Mockito.when(this.planService.findEditMobilePlanById(MODEL_ID)).thenReturn(editMobilePlanModel);
    }

    @Test
    public void getMobilePlanPage_ShouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/mobile"))
                .andExpect(status().isOk())
                .andExpect(view().name("mobile/mobile-all"));
    }

    @Test
    public void addMobilePlan_ShouldAddPlan() throws Exception {

        this.mockMvc
                .perform(post("/mobile/add")
                        .param("name", MODEL_NAME)
                        .param("pricePerMonth", "12")
                        .param("bonuses", "3")
                        .param("planLogo", "aaaa")
                        .param("freeSMS", "10")
                        .param("freeMinutes", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mobile"))
                .andExpect(redirectedUrl("/mobile"));

        ArgumentCaptor<AddMobilePlanModel> captor = ArgumentCaptor.forClass(AddMobilePlanModel.class);
        verify(planService).addNewMobilePlan(captor.capture());
        AddMobilePlanModel model = captor.getValue();
        assertEquals(MODEL_NAME, model.getName());
    }

    @Test
    public void addMobilePlan_InvalidPrice_ShouldFailToAdd() throws Exception {

        this.mockMvc
                .perform(post("/mobile/add")
                        .param("name", "Aaaaaa")
                        .param("pricePerMonth", "0")
                        .param("bonuses", "3")
                        .param("planLogo", "aaaa")
                        .param("freeSMS", "10")
                        .param("freeMinutes", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mobile/add"))
                .andExpect(redirectedUrl("/mobile/add"));

        verifyZeroInteractions(this.planService);
    }

    @Test
    public void editMobilePlan_ShouldEditPlan() throws Exception {

        this.mockMvc
                .perform(post("/mobile/edit/1")
                        .param("pricePerMonth", "12")
                        .param("bonuses", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mobile"))
                .andExpect(redirectedUrl("/mobile"));

        ArgumentCaptor<EditMobilePlanModel> captor = ArgumentCaptor.forClass(EditMobilePlanModel.class);
        verify(planService).editMobilePlan(captor.capture());
        EditMobilePlanModel model = captor.getValue();
        assertEquals(EXPECTED_BONUS, model.getBonuses());
    }

    @Test
    public void editMobilePlan_InvalidPrice_ShouldFailToEditPlan() throws Exception {

        this.mockMvc
                .perform(post("/mobile/edit/1")
                        .param("pricePerMonth", "0")
                        .param("bonuses", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mobile/edit/{id}"))
                .andExpect(redirectedUrl("/mobile/edit/1"));

        verifyZeroInteractions(this.planService);
    }
}