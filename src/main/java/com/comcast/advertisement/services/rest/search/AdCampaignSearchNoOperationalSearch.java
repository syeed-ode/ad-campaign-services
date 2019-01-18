package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.http.ResponseEntity;

import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Named
public class AdCampaignSearchNoOperationalSearch implements AdCampaignSearch {

    @Override
    public ResponseEntity<?> search(AdCampaignSearchRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body("Only duration, ad_content, ad_title are valid");
    }

    public static List<CampaignEntity> noop(AdCampaignSearchRequest request) {
        return new ArrayList<>();
//        return ResponseEntity.status(BAD_REQUEST).body("Only duration, ad_content, ad_title are valid");
    }
}