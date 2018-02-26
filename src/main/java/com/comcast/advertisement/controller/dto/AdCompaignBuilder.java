package com.comcast.advertisement.controller.dto;

import com.comcast.advertisement.campaign.dto.CampaignEntity;

import java.util.Optional;

/**
 * Author: syeedode
 * Date: 2/26/18
 */
public class AdCompaignBuilder {
    private String externalPartnerId;
    private String campaignUuid;
    private String duration;
    private String adContent;
    private String adTitle;
    private String adStatus;

    public AdCompaignBuilder externalPartnerId(String externalPartnerId) {
        this.externalPartnerId = externalPartnerId;
        return this;
    }

    public AdCompaignBuilder campaignUuid(String campaignUuid) {
        this.campaignUuid = campaignUuid;
        return this;
    }

    public AdCompaignBuilder duration(String duration) {
        this.duration = duration;
        return this;
    }

    public AdCompaignBuilder adContent(String adContent) {
        this.adContent = adContent;
        return this;
    }

    public AdCompaignBuilder adTitle(String adTitle) {
        this.adTitle = adTitle;
        return this;
    }

    public AdCompaignBuilder adStatus(String adStatus) {
        this.adStatus = adStatus;
        return this;
    }

    private AdCompaignBuilder(){ }

    public static AdCampaignResponse build(final CampaignEntity entity) {
        AdCompaignBuilder builder = new AdCompaignBuilder();
        String partnerId = Optional
                .ofNullable(entity.getPartnerEntity())
                .map(e -> e.getExternalId())
                .orElse("No partner for this campaign");
        String expirationDate =
                Optional.ofNullable(entity.getExpirationDate())
                        .map(i -> String.valueOf(i))
                        .orElse("Expired for more than a day");
        builder.externalPartnerId(partnerId)
                .campaignUuid(entity.getCampaignUuid())
                .duration(expirationDate)
                .adContent(entity.getCampaignContent())
                .adTitle(entity.getCampaignTitle())
                .adStatus(entity.getCampaignStatus().valueOf());
        return new AdCampaignResponse(builder);
    }

    public String getExternalPartnerId() {
        return externalPartnerId;
    }

    public String getCampaignUuid() {
        return campaignUuid;
    }

    public String getDuration() {
        return duration;
    }

    public String getAdContent() {
        return adContent;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public String getAdStatus() {
        return adStatus;
    }
}
