package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.AdCampaignBaseTest;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.controller.dto.AdCampaignResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@RunWith(MockitoJUnitRunner.class)
public class AdCampaignGetServiceTest implements AdCampaignBaseTest {

    @InjectMocks
    AdCampaignGetService service;

    @Mock
    CampaignRepository campaignRepo;


    @Test
    public void getAdCampain_Success(){
        when(campaignRepo.findByCampaignUuid(GODD_CAMP_UUID)).thenReturn(goodCampaignEntity());

        ResponseEntity<?> response = service.getAdCampain(GODD_CAMP_UUID);

        Assert.assertEquals(OK,response.getStatusCode());
        assertCampaignResponse(new HashSet(Arrays.asList(response.getBody())));
    }

    @Test
    public void getAdCampain_whenNull_returnNotFound(){
        when(campaignRepo.findByCampaignUuid(GODD_CAMP_UUID)).thenReturn(null);

        ResponseEntity<?> response = service.getAdCampain(GODD_CAMP_UUID);

        Assert.assertEquals(NOT_FOUND,response.getStatusCode());
        String body = (String) response.getBody();
        Assert.assertTrue(body.contains("not present"));
    }

    @Test
    public void getAdCampains_whenNullList_returnNoContentResponse(){
        when(campaignRepo.findAll()).thenReturn(null);

        ResponseEntity<?> response = service.getAdCampains();

        Assert.assertEquals(NO_CONTENT,response.getStatusCode());
        Assert.assertEquals(new HashSet<>(),response.getBody());
    }

    @Test
    public void getAdCampains_whenEmptyLiat_returnOkWithEmptySet(){
        when(campaignRepo.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = service.getAdCampains();

        Assert.assertEquals(NO_CONTENT,response.getStatusCode());
        Assert.assertEquals(new HashSet<>(),response.getBody());
    }

    @Test
    public void getAdCampains_whenPopulatedLiat_returnOkData(){
        when(campaignRepo.findAll()).thenReturn(Arrays.asList(goodCampaignEntity()));

        ResponseEntity<?> response = service.getAdCampains();

        Assert.assertEquals(OK,response.getStatusCode());
        Set<AdCampaignResponse> body =  (Set<AdCampaignResponse>) response.getBody();
        assertCampaignResponse(body);
    }

    private void assertCampaignResponse(Set<AdCampaignResponse> responses) {
        AdCampaignResponse result = responses.stream().findFirst().get();
        assertEquals(CONTENT, result.getAdContent());
        assertEquals(TITLE, result.getAdTitle());
        assertEquals(GODD_CAMP_UUID, result.getCampaignUuid());
        Integer time = ACTIVE_TIME + CURRENT_TIME.intValue();
        assertEquals(time.toString(), result.getDuration());
        // assertNotNull(result.getId());
    }
}