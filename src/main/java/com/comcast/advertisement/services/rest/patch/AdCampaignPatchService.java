package com.comcast.advertisement.services.rest.patch;


import com.comcast.advertisement.controller.dto.AdCampaignResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.comcast.advertisement.services.rest.patch.AdCampaignPatchOperation.AD_CONTENT;
import static com.comcast.advertisement.services.rest.patch.AdCampaignPatchOperation.from;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/17/17
 */
@Service
public class AdCampaignPatchService {

//    @Resource
//    private final Map<String, AdCampaignPatchOperation> operations = initializeMap();

    public ResponseEntity<?> patch(Map<String, String> request) {
        request.entrySet().stream()
                .map(entry -> from(entry.getKey()).operate(entry.getValue()))
                .map(entity -> AdCampaignResponse.from(entity))
                .collect(toSet());
        return ResponseEntity.ok("Didn't blow up" + request.entrySet()
                .stream()
                .map(entry -> (" " + entry.getKey().toString() + " " + entry.getKey().toString() + "\n") )
                .collect(joining())
        );
    }

    private Map<String, AdCampaignPatchOperation> initializeMap(){
        Map<String, AdCampaignPatchOperation> map = new HashMap<>();
        map.put("ad_content",AD_CONTENT);
        return map;
    }
}