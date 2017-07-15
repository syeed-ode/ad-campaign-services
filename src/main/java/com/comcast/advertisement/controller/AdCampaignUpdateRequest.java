package com.comcast.advertisement.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/15/17
 */
public class AdCampaignUpdateRequest {
    @JsonProperty("partner_id")
    private String externalPartnerId;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("ad_content")
    private String adContent;

    @JsonProperty("ad_title")
    private String adTitle;

    @JsonProperty("ad_status")
    private String adStatus;

    public String getExternalPartnerId() {
        return externalPartnerId;
    }

    public void setExternalPartnerId(String externalPartnerId) {
        this.externalPartnerId = externalPartnerId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }
}
