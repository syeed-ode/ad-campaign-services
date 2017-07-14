package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.controller.AdCampaignResponse;
import com.comcast.advertisement.exception.AdCampaignNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Service
public class AdCampaignGetService {

    @Autowired
    CampaignRepository campaignRepo;

    public ResponseEntity<?> getAdCampain(String uuid) {
        AdCampaignResponse response =
                Optional.ofNullable(campaignRepo.findByCampaignUuid(uuid))
                        .map(e -> AdCampaignResponse.from(e))
                        .orElseThrow(() -> new AdCampaignNotFoundException(uuid + " not present"));
        return ResponseEntity.ok().body(response);
    }
}
