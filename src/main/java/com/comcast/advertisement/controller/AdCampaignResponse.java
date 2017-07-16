package com.comcast.advertisement.controller;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdCampaignResponse {

    @JsonProperty("partner_id")
    private final String externalPartnerId;

    @JsonProperty("campaign_uuid")
    private final String campaignUuid;

    @JsonProperty("duration")
    private final String duration;

    @JsonProperty("ad_content")
    private final String adContent;

    @JsonProperty("ad_title")
    private final String adTitle;

    @JsonProperty("ad_status")
    private final String adStatus;

    private AdCampaignResponse(String externalPartnerId
            , String campaignUuid
            , String duration
            , String adContent
            , String adTitle
            , String adStatus) {
        this.externalPartnerId = externalPartnerId;
        this.campaignUuid = campaignUuid;
        this.duration = duration;
        this.adContent = adContent;
        this.adTitle = adTitle;
        this.adStatus = adStatus;
    }

    public static AdCampaignResponse from(CampaignEntity entity){
        String partnerId = Optional
                .ofNullable(entity.getPartnerEntity())
                .map(e -> e.getExternalId())
                .orElse("No partner for this campaign");
        String expirationDate =
                Optional.ofNullable(entity.getExpirationDate())
                        .map(i -> String.valueOf(i))
                        .orElse("Expired for more than a day");
        return new AdCampaignResponse(partnerId
                , entity.getCampaignUuid()
                , expirationDate
                , entity.getCampaignContent()
                , entity.getCampaignTitle()
                , entity.getCampaignStatus().valueOf());
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
