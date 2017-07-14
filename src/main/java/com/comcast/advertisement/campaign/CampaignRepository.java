package com.comcast.advertisement.campaign;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query(value = "select * from CAMPAIGN c where c.EXPIRATION_IN_EPOCH = ?1", nativeQuery = true)
    List<CampaignEntity> findActiveCampainsByExpirationDate(@Param("expirationDate") Integer expirationDate);
}
