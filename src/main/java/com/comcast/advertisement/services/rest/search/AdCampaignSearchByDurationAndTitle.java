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
public class AdCampaignSearchByDurationAndTitle implements AdCampaignSearch {
    private final String duration;
    private final String adTitle;

    public AdCampaignSearchByDurationAndTitle(String duration, String adTitle) {
        this.adTitle = adTitle;
        this.duration = duration;
    }

    @Override
    public ResponseEntity<?> search() {
        return null;
    }
}
