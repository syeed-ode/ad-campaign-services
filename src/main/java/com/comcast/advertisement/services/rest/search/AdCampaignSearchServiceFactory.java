package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static com.comcast.advertisement.services.rest.search.SearchOps.*;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Configuration
public class AdCampaignSearchServiceFactory {

    @Inject
    AdCampaignSearchService adCampaignSearchServiceByContent;

    @Inject
    AdCampaignSearchService adCampaignSearchServiceByDuration;

    @Inject
    AdCampaignSearchService adCampaignSearchServiceByDurationAndTitle;

    @Inject
    AdCampaignSearchService adCampaignSearchServiceByTitle;

    @Inject
    AdCampaignSearchService adCampaignSearchNoOperationalSearchService;

    public Set<AdCampaignSearchService> getSearchService(final AdCampaignSearchRequest request) {
        Set<AdCampaignSearchService> searchServiceSet = new HashSet<>();
        boolean noop = true;
        if(!StringUtils.isEmpty(request.getDuration())){
            searchServiceSet.add(getDurationService(request));
            noop = false;
        }
        if(!StringUtils.isEmpty(request.getAdTitle())) {
            searchServiceSet.add(adCampaignSearchServiceByTitle);
            noop = false;
        }
        if(!StringUtils.isEmpty(request.getAdContent())){
            searchServiceSet.add(adCampaignSearchServiceByContent);
            noop = false;
        }
        if(noop) {
            searchServiceSet.add(adCampaignSearchNoOperationalSearchService);
        }
        return searchServiceSet;
    }

    private AdCampaignSearchService getDurationService(AdCampaignSearchRequest request) {
        if(StringUtils.isEmpty(request.getAdTitle())){
            return adCampaignSearchServiceByDuration;
        } else {
            return adCampaignSearchServiceByDurationAndTitle;
        }
    }

    public Set<SearchOps> getSearchEnum(AdCampaignSearchRequest request) {
        Set<SearchOps> searchOpsSet = new HashSet<>();
        boolean noop = true;
        if(!StringUtils.isEmpty(request.getDuration())){
            searchOpsSet.add(getDurationEnum(request));
            noop = false;
        }

        if(!StringUtils.isEmpty(request.getAdTitle())) {
            searchOpsSet.add(AD_TITLE);
            noop = false;
        }

        if(!StringUtils.isEmpty(request.getAdContent())){
            searchOpsSet.add(AD_CONTENT);
            noop = false;
        }

        if(noop) {
            searchOpsSet.add(NO_OP);
        }

        return searchOpsSet;
    }

    private SearchOps getDurationEnum(AdCampaignSearchRequest request) {
        if(StringUtils.isEmpty(request.getAdTitle())){
            return DURATION;
        } else {
            return DURATION_TITLE;
        }
    }
}
