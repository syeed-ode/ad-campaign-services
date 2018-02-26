package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import com.comcast.advertisement.controller.dto.AdCompaignBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@Named
public class AdCampaignSearchByContent implements AdCampaignSearch {

    @Autowired
    CampaignRepository campaignRepo;

    @Override
    public ResponseEntity<?> search(AdCampaignSearchRequest request) {
        String content = request.getAdContent();
        List<CampaignEntity> campaignEntityByContent = campaignRepo.findByCampaignContent(content);
        if(CollectionUtils.isEmpty(campaignEntityByContent)){
            return ResponseEntity.status(NOT_FOUND).body("No entries found matchng content: " + content);
        }
        return ResponseEntity.ok().body(campaignEntityByContent
                .stream()
                .map(AdCompaignBuilder::build)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
    }
}