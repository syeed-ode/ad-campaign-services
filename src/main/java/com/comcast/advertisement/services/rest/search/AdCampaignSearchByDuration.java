package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.utilities.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.comcast.advertisement.controller.AdCampaignResponse.from;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Service
public class AdCampaignSearchByDuration implements AdCampaignSearch {
    @Autowired
    CampaignRepository campaignRepo;

    @Autowired
    DateUtility dateTime;

    private final String duration;

    public AdCampaignSearchByDuration(String timeDuraton) {
        this.duration = timeDuraton;
    }

    @Override
    public ResponseEntity<?> search() {

        Integer timeToLookFor = dateTime.getTimeInEpoch() - Integer.valueOf(duration);
        List<CampaignEntity> activeCampainsByDate = campaignRepo.findActiveCampainsByExpirationDate(timeToLookFor);
        if(CollectionUtils.isEmpty(activeCampainsByDate)){
            return ResponseEntity.status(NOT_FOUND).body("No entries found matchng duration: " + duration);
        }
        return ResponseEntity.ok().body(activeCampainsByDate
                .stream()
                .map(e -> from(e))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
    }
}
