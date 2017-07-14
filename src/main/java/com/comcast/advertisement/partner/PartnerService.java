package com.comcast.advertisement.partner;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.campaign.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.ACTIVE;
import static com.comcast.advertisement.campaign.CampaignStatusEnum.INACTIVE;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@Service
public class PartnerService {
    @Autowired
    PartnerRepository partnerRepo;

    @Autowired
    CampaignRepository campaignRepo;

    public PartnerEntity determinePartnerCampainAssociation(PartnerEntity partnerEntity, CampaignEntity campaignEntity) {

        if(ACTIVE.equals(campaignEntity.getCampaignStatus())) {

            for(CampaignEntity c : partnerEntity.getCampaignEntitySet()) {
                if(c.equals(campaignEntity)) {
                    c.setCampaignStatus(ACTIVE);
                    campaignRepo.save(c);
                } else if(ACTIVE.equals(c.getCampaignStatus())) {
                    c.setCampaignStatus(INACTIVE);
                    campaignRepo.save(c);
                }
            }
        }
        partnerEntity.getCampaignEntitySet().add(campaignEntity);
        campaignEntity.setPartnerEntity(partnerEntity);
        return partnerRepo.save(partnerEntity);
    }
}
