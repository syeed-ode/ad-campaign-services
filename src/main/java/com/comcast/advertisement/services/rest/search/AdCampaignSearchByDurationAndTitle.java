package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import com.comcast.advertisement.utilities.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.comcast.advertisement.controller.AdCampaignResponse.from;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@Named
public class AdCampaignSearchByDurationAndTitle implements AdCampaignSearch {

    @Autowired
    CampaignRepository campaignRepo;

    @Autowired
    DateUtility dateTime;


    @Override
    public ResponseEntity<?> search(AdCampaignSearchRequest request) {
        String duration = request.getDuration();
        String title = request.getAdTitle();
        Integer timeToLookFor = dateTime.getTimeInEpoch() - Integer.valueOf(duration);
        List<CampaignEntity> activeCampainsByDate = campaignRepo
                .findByCampaignTitleAndExpirationDateIsGreaterThanEqual(title, timeToLookFor);
        if(CollectionUtils.isEmpty(activeCampainsByDate)){
            return ResponseEntity.status(NOT_FOUND)
                    .body("No entries found matchng duration: " + duration
                            + " or title: " + title);
        }
        return ResponseEntity.ok().body(activeCampainsByDate
                .stream()
                .map(e -> from(e))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
    }
}
