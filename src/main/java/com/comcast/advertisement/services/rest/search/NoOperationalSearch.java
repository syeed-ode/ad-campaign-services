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
public class NoOperationalSearch implements AdCampaignSearch {
    private final String defultMessage;

    public NoOperationalSearch(String s) {
        defultMessage = s;
    }

    @Override
    public ResponseEntity<?> search() {
        return ResponseEntity.badRequest().body(defultMessage);
    }
}
