package com.comcast.advertisement.controller;

import com.comcast.advertisement.services.rest.AdCampaignCreateService;
import com.comcast.advertisement.validation.AdValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/adcampaign/{uuid}", method = RequestMethod.GET)
    public String linkListAdder(String uuid) {
            return "Hello World\n" + uuid;
    }

    @RequestMapping(value = "/adcampaign", method = RequestMethod.POST)
    public ResponseEntity<?> linkListAdder(@RequestBody @Valid AdCampaignRequest request, BindingResult result) {
        validationService.validateCreateRequest(result);
        return createService.create(request);
    }
}
