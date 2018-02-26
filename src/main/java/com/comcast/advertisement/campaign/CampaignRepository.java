package com.comcast.advertisement.campaign;

import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.partner.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Integer> {
    CampaignEntity findByCampaignTitle(String campaignTitle);
    CampaignEntity findByCampaignUuid(String campaignUuid);
    CampaignEntity findByCampaignStatusAndPartnerEntity(CampaignStatusEnum campaignStatus, PartnerEntity partnerEntity);


    List<CampaignEntity> findAll();
    List<CampaignEntity> findByExpirationDateIsGreaterThanEqual(Integer expirationDate);
    List<CampaignEntity> findByCampaignTitleAndExpirationDateIsGreaterThanEqual(String campaignTitle, Integer expirationDate);
    List<CampaignEntity> findByCampaignContent(String campaignContent);
}
