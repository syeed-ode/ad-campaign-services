package com.comcast.advertisement.services.rest.search;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Service
public class AdCampaignSearchByTitle implements AdCampaignSearch {
    private final String adTitle;

    public AdCampaignSearchByTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    @Override
    public ResponseEntity<?> search() {
        return null;
    }
}
