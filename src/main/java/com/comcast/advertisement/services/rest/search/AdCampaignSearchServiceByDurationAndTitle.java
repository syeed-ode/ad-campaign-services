package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import com.comcast.advertisement.controller.dto.AdCompaignBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.comcast.advertisement.dal.JPAUtility.getEntityManager;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@Named
public class AdCampaignSearchServiceByDurationAndTitle implements AdCampaignSearchService {

    private static final String QUERY_STRING = "select c from CAMPAIGN c " +
            "where c.expirationDate = ?1 " +
            "and c.campaignTitle = ?2";

    @Autowired
    CampaignRepository campaignRepo;

    @Override
    public List<CampaignEntity> search(AdCampaignSearchRequest request) {
        String duration = request.getDuration();
        String title = request.getAdTitle();
        Integer timeToLookFor = Integer.valueOf(duration);
        return campaignRepo
                .findByCampaignTitleAndExpirationDateIsGreaterThanEqual(title, timeToLookFor);
    }

    public static List<CampaignEntity> durationAndTitles(final AdCampaignSearchRequest request) {
        final String duration = request.getDuration();
        final String title = request.getAdTitle();
        final Integer timeToLookFor = Integer.valueOf(duration);
        Query query = getQuery(title, timeToLookFor);
        List<CampaignEntity> activeCampainsByDate = (List<CampaignEntity>) query.getResultList();
        return activeCampainsByDate;
    }

    private static Query getQuery(String title, Integer timeToLookFor) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(QUERY_STRING);
        query.setParameter(1, timeToLookFor);
        query.setParameter(2, title);
        return query;
    }
}
