package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.controller.AdCampaignResponse;
import com.comcast.advertisement.exception.AdCampaignNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.comcast.advertisement.controller.AdCampaignResponse.from;
import static java.util.stream.Collectors.toSet;

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
                        .map(e -> from(e))
                        .orElseThrow(() -> new AdCampaignNotFoundException(uuid + " not present"));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> getAdCampains() {
        Set<AdCampaignResponse> set = campaignRepo.findAll().stream()
                .filter(Objects::nonNull)
                .map(ent -> from(ent))
                .collect(toSet());
        if(CollectionUtils.isEmpty(set)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No campaigns found");
        }
        return ResponseEntity.ok().body(set);
    }
}
