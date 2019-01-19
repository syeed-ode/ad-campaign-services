package com.comcast.advertisement.controller;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.services.rest.AdCampaignCreateService;
import com.comcast.advertisement.services.rest.AdCampaignGetService;
import com.comcast.advertisement.services.rest.AdCampaignUpdateService;
import com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceFactory;
import com.comcast.advertisement.validation.AdValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static com.comcast.advertisement.controller.dto.AdCompaignBuilder.build;
import static com.comcast.advertisement.utilities.AdCampaignConstants.AD_CAMPAIGNS;
import static java.util.stream.Collectors.toSet;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/26/17
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration

@RunWith(SpringRunner.class)
@WebMvcTest(AdCampaignController.class)
public class POST_AdCampaignControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    AdValidationService validationService;

    @MockBean
    AdCampaignCreateService createService;

    @MockBean
    AdCampaignSearchServiceFactory searchFactory;

    @MockBean
    AdCampaignUpdateService updateService;

    @MockBean
    AdCampaignPatchService patchService;

    @MockBean
    AdCampaignGetService getService;

    @Test
    public void testExample() throws Exception {
        given(this.getService.getAdCampains())
                .willReturn((ResponseEntity)getResponseSet());

        this.mvc.perform(get(AD_CAMPAIGNS))
                .andExpect(status().isOk());
    }

    private ResponseEntity<?> getResponseSet() {
        return ResponseEntity.ok().body(Stream.of(build(new CampaignEntity())).collect(toSet()));
    }

}