package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.CampaignStatusEnum;
import com.comcast.advertisement.controller.AdCampaignUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.ACTIVE;
import static com.comcast.advertisement.campaign.CampaignStatusEnum.INACTIVE;
import static com.comcast.advertisement.campaign.CampaignStatusEnum.from;
import static com.comcast.advertisement.controller.AdCampaignResponse.from;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */

@Service
public class AdCampaignUpdateService {
    @Autowired
    CampaignRepository campaignRepo;

    public ResponseEntity<?> updateCampaign(AdCampaignUpdateRequest updateRequest, String uuid) {
        CampaignEntity campaignEntity = campaignRepo.findByCampaignUuid(uuid);
        String externalPartnerId = updateRequest.getExternalPartnerId();

        ResponseEntity<?> invalidResponse = validateRequest(campaignEntity, uuid, externalPartnerId);
        if (Objects.nonNull(invalidResponse)) return invalidResponse;


        if(Objects.nonNull(updateRequest.getAdContent())) {
            campaignEntity.setCampaignContent(updateRequest.getAdContent());
        }
        if(Objects.nonNull(updateRequest.getAdTitle())) {
            campaignEntity.setCampaignTitle(updateRequest.getAdTitle());
        }
        if(Objects.nonNull(updateRequest.getDuration())) {
            campaignEntity.setExpirationDate(updateRequest.getDuration());
        }

        processCampaignStatus(updateRequest, campaignEntity);

        campaignRepo.save(campaignEntity);
        return ResponseEntity.ok(from(campaignEntity));
    }

    private void processCampaignStatus(AdCampaignUpdateRequest updateRequest, CampaignEntity entity) {
        if(Objects.isNull(updateRequest.getAdStatus())){
            return;
        }
        CampaignStatusEnum requestedStatus = from(updateRequest.getAdStatus());
        if(ACTIVE.equals(requestedStatus)) {
            CampaignEntity currentlyActive = campaignRepo.findByCampaignStatusAndPartnerEntity(ACTIVE, entity.getPartnerEntity());
            if(!currentlyActive.equals(entity)){
                currentlyActive.setCampaignStatus(INACTIVE);
                campaignRepo.save(currentlyActive);
            }
        }
        entity.setCampaignStatus(requestedStatus);
    }

    private ResponseEntity<?> validateRequest(CampaignEntity campaignEntity, String uuid, String externalPartnerId) {
        if(Objects.isNull(campaignEntity)){
            return ResponseEntity.status(NOT_FOUND).body("Campaign: " + uuid + " does not exist");
        }

        if(Objects.nonNull(externalPartnerId)){
            return ResponseEntity.status(FORBIDDEN).body("partner_id may not be modified");
        }
        return null;
    }
}
