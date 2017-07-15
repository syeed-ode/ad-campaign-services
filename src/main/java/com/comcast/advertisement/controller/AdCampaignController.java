package com.comcast.advertisement.controller;

import com.comcast.advertisement.services.rest.AdCampaignCreateService;
import com.comcast.advertisement.services.rest.AdCampaignGetService;
import com.comcast.advertisement.services.rest.AdCampaignUpdateService;
import com.comcast.advertisement.services.rest.search.AdCampaignSearch;
import com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceFactory;
import com.comcast.advertisement.validation.AdValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @RequestMapping(value = "/adcampaign", method = RequestMethod.GET)
    public ResponseEntity<?> getCampaign(@RequestParam(value="uuid") String uuidRequested) {
        return getService.getAdCampain(uuidRequested);
    }

    @RequestMapping(value = "/adcampaigns", method = RequestMethod.GET)
    public ResponseEntity<?> getCampaigns() {
        return getService.getAdCampains();
    }

    @RequestMapping(value = "/adcampaign", method = RequestMethod.POST)
    public ResponseEntity<?> createAdCampaign(@RequestBody @Valid AdCampaignCreateRequest request, BindingResult result) {
        validationService.validateCreateRequest(result);
        return createService.create(request);
    }


    @RequestMapping(value = "/adcampaign/search", method = RequestMethod.POST)
    public ResponseEntity<?> findAdCampaign(@RequestBody AdCampaignSearchRequest searchRequest) {
        AdCampaignSearch searchService = searchFactory.getSearchService(searchRequest);
        return searchService.search(searchRequest);
    }

    @RequestMapping(value = "/adcampaign/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAdCampaign(@RequestBody AdCampaignUpdateRequest updateRequest, @PathVariable("uuid") String uuid) {
        return updateService.updateCampaign(updateRequest, uuid);
    }
}
