package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.inject.Inject;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Configuration
public class AdCampaignSearchServiceFactory {

    @Inject
    AdCampaignSearch adCampaignSearchByContent;

    @Inject
    AdCampaignSearch adCampaignSearchByDuration;

    @Inject
    AdCampaignSearch adCampaignSearchByDurationAndTitle;

    @Inject
    AdCampaignSearch adCampaignSearchByTitle;

    @Inject
    AdCampaignSearch adCampaignSearchNoOperationalSearch;

    public AdCampaignSearch getSearchService(AdCampaignSearchRequest request) {
        if(!StringUtils.isEmpty(request.getDuration())){
            return getDurationService(request);
        } else if(!StringUtils.isEmpty(request.getAdTitle())) {
            return adCampaignSearchByTitle;
        } else if(!StringUtils.isEmpty(request.getAdContent())){
            return adCampaignSearchByContent;
        }
        return adCampaignSearchNoOperationalSearch;
    }

    private AdCampaignSearch getDurationService(AdCampaignSearchRequest request) {
        if(StringUtils.isEmpty(request.getAdTitle())){
            return adCampaignSearchByDuration;
        } else {
            return adCampaignSearchByDurationAndTitle;
        }
    }
}
