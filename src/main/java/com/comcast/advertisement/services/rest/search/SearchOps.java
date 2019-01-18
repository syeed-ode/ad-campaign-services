package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

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
        public List<CampaignEntity> performSerch(AdCampaignSearchRequest r){
            return content(r);
        }
    },
    DURATION{
        public List<CampaignEntity> performSerch(AdCampaignSearchRequest r){
            return duration(r);
        }
    },
    AD_TITLE{
        public List<CampaignEntity> performSerch(AdCampaignSearchRequest r){
            return title(r);
        }
    },
    DURATION_TITLE{
        public List<CampaignEntity> performSerch(AdCampaignSearchRequest r){
            return durationAndTitles(r);
        }
    },
    NO_OP{
        public List<CampaignEntity> performSerch(AdCampaignSearchRequest r){
            return noop(r);
        }
    };

    public abstract List<CampaignEntity> performSerch(AdCampaignSearchRequest r);
}
