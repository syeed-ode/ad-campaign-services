package com.comcast.advertisement.partner;

import com.comcast.advertisement.campaign.CampaignEntity;
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

    public PartnerEntity determinePartnerCampainAssociation(PartnerEntity partnerEntity, CampaignEntity campaignEntity) {
        boolean foundMatch = false;
        if(!ACTIVE.equals(campaignEntity.getCampaignStatus())) {
            partnerEntity.getCampaignEntitySet().add(campaignEntity);
            return partnerRepo.save(partnerEntity);
        }

        for(CampaignEntity c : partnerEntity.getCampaignEntitySet()) {
            if(c.equals(campaignEntity)) {
                foundMatch = true;
                c.setCampaignStatus(ACTIVE);
            } else {
                c.setCampaignStatus(INACTIVE);
            }
        }
        if(!foundMatch){
            partnerEntity.getCampaignEntitySet().add(campaignEntity);
            campaignEntity.setPartnerEntity(partnerEntity);
        }
        return partnerRepo.save(partnerEntity);
    }
}
