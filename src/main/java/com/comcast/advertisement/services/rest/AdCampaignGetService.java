package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.controller.dto.AdCompaignBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.util.CollectionUtils.isEmpty;

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
        return Optional.ofNullable(campaignRepo.findByCampaignUuid(uuid))
                        .map(AdCompaignBuilder::build)
                        .map(res -> {
                            ResponseEntity r = ResponseEntity.ok().body(res);
                            return r;
                        })
                        .orElse(ResponseEntity.status(NOT_FOUND).body(uuid + " not present"));
    }

    public ResponseEntity<?> getAdCampains() {
        return Optional.ofNullable(campaignRepo.findAll())
                .map(entityList -> entityList.stream()
                        .map(AdCompaignBuilder::build)
                        .collect(toSet()))
                .filter(set -> !isEmpty(set))
                .map(set -> ResponseEntity.ok().body(set))
                .orElse(ResponseEntity.status(NO_CONTENT).body(new HashSet<>()));
    }
}
