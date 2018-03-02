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

    public Set<SearchOps> getSearchEnum(AdCampaignSearchRequest request) {
        Set<SearchOps> searchOpsSet = new HashSet<>();
        if(!StringUtils.isEmpty(request.getDuration())){
            searchOpsSet.add(getDurationEnum(request));
        } else if(!StringUtils.isEmpty(request.getAdTitle())) {
            searchOpsSet.add(AD_TITLE);
        } else if(!StringUtils.isEmpty(request.getAdContent())){
            searchOpsSet.add(AD_CONTENT);
        } else {
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
