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
public class AdCampaignSearchNoOperationalSearchService implements AdCampaignSearchService {

    @Override
    public List<CampaignEntity> search(AdCampaignSearchRequest request) {
        return new ArrayList<>();
    }

    public static List<CampaignEntity> noop(AdCampaignSearchRequest request) {
        return new ArrayList<>();
    }
}