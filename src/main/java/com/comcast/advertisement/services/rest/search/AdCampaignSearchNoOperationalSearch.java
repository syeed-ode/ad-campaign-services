package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import javax.inject.Named;

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
}