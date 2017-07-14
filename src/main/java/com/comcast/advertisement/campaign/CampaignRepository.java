package com.comcast.advertisement.campaign;

import com.comcast.advertisement.partner.PartnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Repository
public interface CampaignRepository extends CrudRepository<CampaignEntity, Integer> {
    CampaignEntity findByCampaignTitle(String campaignTitle);
    CampaignEntity findByCampaignUuid(String campaignUuid);
}
