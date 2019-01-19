package com.comcast.advertisement.controller;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.services.rest.AdCampaignCreateService;
import com.comcast.advertisement.services.rest.AdCampaignGetService;
import com.comcast.advertisement.services.rest.AdCampaignUpdateService;
import com.comcast.advertisement.services.rest.search.AdCampaignSearchService;
import com.comcast.advertisement.services.rest.search.AdCampaignSearchRespBuilder;
import com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceFactory;
import com.comcast.advertisement.validation.AdValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.comcast.advertisement.utilities.AdCampaignConstants.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@RestController
public class AdCampaignController {

    @Autowired
    AdValidationService validationService;

    @Autowired
    AdCampaignCreateService createService;

    @Autowired
    AdCampaignGetService getService;

    @Autowired
    AdCampaignSearchServiceFactory searchFactory;

    @Autowired
    AdCampaignUpdateService updateService;

    @Autowired
    AdCampaignSearchRespBuilder builder;

    @RequestMapping(value = AD_CAMPAIGN_UUID_PARAM, method = GET)
    public ResponseEntity<?> getCampaign(@PathVariable("uuid")  String uuidRequested) {
        return getService.getAdCampain(uuidRequested);
    }

    @RequestMapping(value = AD_CAMPAIGNS, method = GET)
    public ResponseEntity<?> getCampaigns() {
        return getService.getAdCampains();
    }

    @RequestMapping(value = AD_CAMPAIGN, method = POST)
    public ResponseEntity<?> createAdCampaign(@RequestBody @Valid AdCampaignCreateRequest request, BindingResult result) {
        validationService.validateCreateRequest(result);
        return createService.create(request);
    }

    @RequestMapping(value = AD_CAMPAIGN_SEARCH, method = GET)
    public ResponseEntity<?> findAdCampaign(@RequestBody AdCampaignSearchRequest searchRequest) {
        Set<AdCampaignSearchService> searchServiceSet = searchFactory.getSearchService(searchRequest);
        return builder.buildSearchResponse(searchServiceSet.stream()
                .map(searchService -> searchService.search(searchRequest))
                .flatMap(list -> list.stream())
                .collect(Collectors.toSet()));
    }

    @RequestMapping(value = AD_CAMPAIGN_SEARCH_ENUM, method = GET)
    public ResponseEntity<?> findAdCampaignByEnum(@RequestBody AdCampaignSearchRequest searchRequest) {
         Set<CampaignEntity> entitySet = (Set<CampaignEntity>) null;
         return builder.buildSearchResponse(searchFactory
                 .getSearchEnum(searchRequest)
                 .stream()
                 .map(ops -> ops.performSerch(searchRequest))
                 .flatMap(list -> list.stream())
                 .collect(Collectors.toSet()));
    }

    @RequestMapping(value = AD_CAMPAIGN_UUID_PARAM, method = PUT)
    public ResponseEntity<?> updateAdCampaign(@RequestBody AdCampaignUpdateRequest updateRequest, @PathVariable("uuid") String uuid) {
        return updateService.updateCampaign(updateRequest, uuid);
    }
}
