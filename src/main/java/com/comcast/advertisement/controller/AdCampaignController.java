package com.comcast.advertisement.controller;

import com.comcast.advertisement.services.rest.AdCampaignCreateService;
import com.comcast.advertisement.services.rest.AdCampaignGetService;
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

    @RequestMapping(value = "/adcampaign", method = RequestMethod.GET)
    public ResponseEntity<?> linkListAdder(@RequestParam(value="uuid") String uuidRequested) {
        return getService.getAdCampain(uuidRequested);
    }

    @RequestMapping(value = "/adcampaign", method = RequestMethod.POST)
    public ResponseEntity<?> linkListAdder(@RequestBody @Valid AdCampaignCreateRequest request, BindingResult result) {
        validationService.validateCreateRequest(result);
        return createService.create(request);
    }
}
