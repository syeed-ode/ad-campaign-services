package com.comcast.advertisement.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdCampaignCreateRequest {
    // unique_string_representing_partner
    @NotNull
    @JsonProperty("partner_id")
    private String externalPartnerId;

    // int_representing_campaign_duration_in_seconds_from_now
    @Min(86400)
    @JsonProperty("duration")
    private int duration;

    // string_of_content_to_display_as_ad",
    @NotNull
    @JsonProperty("ad_content")
    private String adContent;

    //  string_of_title_to_display
    @NotNull
    @JsonProperty("ad_title")
    private String adTitle;

    // string_of_status_indicating_Active_or_Inactive
    @NotNull
    @JsonProperty("ad_status")
    private String adStatus;

    public String getExternalPartnerId() {
        return externalPartnerId;
    }

    public void setExternalPartnerId(String externalPartnerId) {
        this.externalPartnerId = externalPartnerId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
