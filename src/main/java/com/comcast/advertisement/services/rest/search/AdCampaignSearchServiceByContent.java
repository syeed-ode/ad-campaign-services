package com.comcast.advertisement.services.rest.search;

import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignSearchRequest;
import com.comcast.advertisement.controller.dto.AdCompaignBuilder;
import com.comcast.advertisement.dal.JPAUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
@Named
public class AdCampaignSearchServiceByContent implements AdCampaignSearchService {

    @Autowired
    CampaignRepository campaignRepo;
    private static final String queryString = "SELECT c FROM CAMPAIGN c WHERE c.campaignContent = :content";

    @Override
    public List<CampaignEntity> search(AdCampaignSearchRequest request) {
        String content = request.getAdContent();
        return campaignRepo.findByCampaignContent(content);
    }

    public static List<CampaignEntity> content(final AdCampaignSearchRequest request) {
        final String content = request.getAdContent();
        TypedQuery<CampaignEntity> query = JPAUtility.getEntityManager().createQuery(queryString, CampaignEntity.class);
        query.setParameter("content", content);
        List<CampaignEntity> campaignEntityByContent = query.getResultList();
        return campaignEntityByContent;
    }
}