package com.comcast.advertisement.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdCampaignSearchRequest {
    @JsonProperty("duration")
    private String duration;

    @JsonProperty("ad_content")
    private String adContent;

    @JsonProperty("ad_title")
    private String adTitle;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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
}
