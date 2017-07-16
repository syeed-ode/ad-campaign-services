package com.comcast.advertisement;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.partner.PartnerEntity;

import java.util.Date;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.from;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
public interface AdCampaignBaseTest {
     String PARTNER_ID          = "2300430";
     String CONTENT             = "Gppd content";
     String TITLE               = "Good title to use";
     Integer ACTIVE_TIME        = 86400;
     String GODD_PART_UUID      = "4d106688-this-isa-good-partnerid";

     String CAMPAIGN_TITLE      = "Good title";
     String GODD_CAMP_UUID      = "4d106688-this-isa-good-campainid";
     Long CURRENT_TIME          = new Date().getTime();
     String INItiAL_CAMP_STATUS = "inactive";

    default CampaignEntity goodCampaignEntity() {
        CampaignEntity campaignEntity = new CampaignEntity();
        campaignEntity.setPartnerEntity(goodPartnerEntity());
        campaignEntity.setCampaignStatus(from(INItiAL_CAMP_STATUS));
        campaignEntity.setCampaignUuid(GODD_CAMP_UUID);
        campaignEntity.setExpirationDate(ACTIVE_TIME + CURRENT_TIME.intValue());
        campaignEntity.setCampaignContent(CONTENT);
        campaignEntity.setId(1);
        campaignEntity.setCampaignTitle(TITLE);
        return campaignEntity;
    }

    default PartnerEntity goodPartnerEntity() {
        PartnerEntity partnerEntity = new PartnerEntity();
        partnerEntity.setExternalId(PARTNER_ID);
        partnerEntity.setPartnerUuid(GODD_PART_UUID);
        partnerEntity.setId(1);
        return partnerEntity;
    }
}
