package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

import static com.comcast.advertisement.controller.dto.AdCompaignBuilder.build;
import static com.comcast.advertisement.dal.JPAUtility.getEntityManager;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@Named
public class AdCampaignSearchByTitle implements AdCampaignSearch {

    private static final String QUERY_STRING = "select c from CAMPAIGN c where c.campaignTitle = ?1";

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

    public static List<CampaignEntity> title(final AdCampaignSearchRequest request) {
        final String title = request.getAdTitle();
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(QUERY_STRING);
        query.setParameter(1, title);
        List<CampaignEntity> campaignEntiyByTitle = (List<CampaignEntity>) query.getResultList();
        return campaignEntiyByTitle;
    }
}
