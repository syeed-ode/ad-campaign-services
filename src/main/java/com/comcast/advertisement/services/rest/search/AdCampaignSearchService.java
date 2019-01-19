package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
public interface AdCampaignSearchService {
    List<CampaignEntity> search(AdCampaignSearchRequest request);
}
