package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Objects;

import static com.comcast.advertisement.controller.dto.AdCompaignBuilder.build;
import static com.comcast.advertisement.dal.JPAUtility.getEntityManager;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@Named
public class AdCampaignSearchByTitle implements AdCampaignSearch {

    @Autowired
    CampaignRepository campaignRepo;

    @Override
    public ResponseEntity<?> search(AdCampaignSearchRequest request) {
        String title = request.getAdTitle();
        CampaignEntity campaignEntiyByTitle = campaignRepo.findByCampaignTitle(title);
        if(Objects.isNull(campaignEntiyByTitle)){
            return ResponseEntity.status(NOT_FOUND).body("No entries found matching title: " + title);
        }
        return ResponseEntity.ok().body(build(campaignEntiyByTitle));
    }

    public static ResponseEntity<?> title(AdCampaignSearchRequest request) {
        String title = request.getAdTitle();
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select * " +
                        "from CAMPAIGN " +
                        "where title = ?");
        query.setParameter(1, title);
        CampaignEntity campaignEntiyByTitle = (CampaignEntity) query.getSingleResult();
        if(Objects.isNull(campaignEntiyByTitle)){
            return ResponseEntity.status(NOT_FOUND).body("No entries found matching title: " + title);
        }
        return ResponseEntity.ok().body(build(campaignEntiyByTitle));
    }
}
