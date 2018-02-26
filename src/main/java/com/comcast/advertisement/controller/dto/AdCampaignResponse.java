package com.comcast.advertisement.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    protected AdCampaignResponse(AdCompaignBuilder builder) {
        this.externalPartnerId = builder.getExternalPartnerId();
        this.campaignUuid = builder.getCampaignUuid();
        this.duration = builder.getDuration();
        this.adContent = builder.getAdContent();
        this.adTitle = builder.getAdTitle();
        this.adStatus = builder.getAdStatus();
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
