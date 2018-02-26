package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.campaign.CampaignDto;
import com.comcast.advertisement.campaign.CampaignRepository;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignCreateRequest;
import com.comcast.advertisement.partner.PartnerEntity;
import com.comcast.advertisement.partner.PartnerRepository;
import com.comcast.advertisement.partner.PartnerService;
import com.comcast.advertisement.utilities.AdCampaingUuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static com.comcast.advertisement.campaign.CampaignDto.fromRequest;
import static com.comcast.advertisement.campaign.CampaignStatusEnum.from;
import static com.comcast.advertisement.campaign.dto.CampaignBuilder.build;
import static com.comcast.advertisement.controller.dto.AdCompaignBuilder.build;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Service
public class AdCampaignCreateService {

    @Autowired
    CampaignRepository campaignRepo;

    @Autowired
    PartnerRepository partnerRepo;

    @Autowired
    AdCampaingUuidGenerator uuidGenerator;

    @Autowired
    PartnerService patnerService;

    public ResponseEntity<?> create(AdCampaignCreateRequest validRequest) {
        PartnerEntity partner = createPartner(validRequest.getExternalPartnerId());
        CampaignEntity campaign = createCampaign(fromRequest(validRequest));
        partner = patnerService.determinePartnerCampainAssociation(partner, campaign);
        return ResponseEntity.created(UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1")
                .path("adcampaign/")
                .path(partner.getPartnerUuid())
                .build().toUri()).body(build(campaign));
    }

    protected PartnerEntity createPartner(String externalPartnerId) {
        PartnerEntity partnerEntity = partnerRepo.findByExternalId(externalPartnerId);
        if(Objects.isNull(partnerEntity)) {
            PartnerEntity p = new PartnerEntity();
            p.setExternalId(externalPartnerId);
            p.setPartnerUuid(uuidGenerator.uuid());
            partnerEntity = partnerRepo.save(p);
        }
        return partnerEntity;
    }

    /**
     * Do not save status here.  This is done during status processing.
     */
    protected CampaignEntity createCampaign(CampaignDto dto) {
        CampaignEntity campaignEntity = campaignRepo.findByCampaignTitle(dto.getCampainTitle());
        if(Objects.isNull(campaignEntity)) {
            CampaignEntity campain = build(dto, uuidGenerator.uuid());
            campaignEntity = campaignRepo.save(campain);
        }
        campaignEntity.setCampaignStatus(from(dto.getAdStatus()));
        return campaignEntity;
    }

}
