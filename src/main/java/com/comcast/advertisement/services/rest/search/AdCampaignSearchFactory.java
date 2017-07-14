package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.controller.AdCampaignResponse;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Service
public class AdCampaignSearchFactory {

    @Bean
    public AdCampaignSearch getSearchService(AdCampaignSearchRequest request) {
        if(!StringUtils.isEmpty(request.getDuration())){
            if(StringUtils.isEmpty(request.getAdTitle())){
                return new AdCampaignSearchByDuration(request.getDuration());
            } else {
                return new AdCampaignSearchByDurationAndTitle(request.getDuration(),request.getAdTitle());
            }
        } else if(!StringUtils.isEmpty(request.getAdTitle())){
            return new AdCampaignSearchByTitle(request.getAdTitle());
        } else if(!StringUtils.isEmpty(request.getAdContent())){
            return new NoOperationalSearch(request.getAdContent());
        }
        return new NoOperationalSearch("Invalid Fields Provided");
    }
}
