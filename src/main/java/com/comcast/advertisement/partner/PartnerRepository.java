package com.comcast.advertisement.partner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Repository
public interface PartnerRepository extends CrudRepository<PartnerEntity, Integer> {
    PartnerEntity findByExternalId(String externalId);
}
