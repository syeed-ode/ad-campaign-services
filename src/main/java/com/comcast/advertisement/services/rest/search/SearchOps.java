package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.http.ResponseEntity;

import static com.comcast.advertisement.services.rest.search.AdCampaignSearchByContent.content;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchByDuration.duration;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchByDurationAndTitle.durationAndTitles;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchByTitle.title;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchNoOperationalSearch.noop;

/**
 * Author: syeedode
 * Date: 2/27/18
 */
public enum SearchOps {
    AD_CONTENT {
        public ResponseEntity<?> performSerch(AdCampaignSearchRequest r){
            return content(r);
        }
    },
    DURATION{
        public ResponseEntity<?> performSerch(AdCampaignSearchRequest r){
            return duration(r);
        }
    },
    AD_TITLE{
        public ResponseEntity<?> performSerch(AdCampaignSearchRequest r){
            return title(r);
        }
    },
    DURATION_TITLE{
        public ResponseEntity<?> performSerch(AdCampaignSearchRequest r){
            return durationAndTitles(r);
        }
    },
    NO_OP{
        public ResponseEntity<?> performSerch(AdCampaignSearchRequest r){
            return noop(r);
        }
    };

    public abstract ResponseEntity<?> performSerch(AdCampaignSearchRequest r);
}
