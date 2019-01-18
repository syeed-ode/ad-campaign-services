package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.dto.AdCampaignResponse;
import com.comcast.advertisement.controller.dto.AdCompaignBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AdCampaignSearchRespBuilder {
    public ResponseEntity<?> buildSearchResponse(Set<CampaignEntity> entitySet) {
        if(CollectionUtils.isEmpty(entitySet)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
        }
        return ResponseEntity.ok().body(entitySet
                .stream()
                .map(AdCompaignBuilder::build)
                .collect(Collectors.toSet()));

    }
}
