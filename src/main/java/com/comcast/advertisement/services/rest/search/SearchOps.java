package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;

import java.util.List;

import static com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceByContent.content;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceByDuration.duration;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceByDurationAndTitle.durationAndTitles;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchServiceByTitle.title;
import static com.comcast.advertisement.services.rest.search.AdCampaignSearchNoOperationalSearchService.noop;

/**
 * Author: syeedode
 * Date: 2/27/18
 *
 * LOOK FOR ME
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
