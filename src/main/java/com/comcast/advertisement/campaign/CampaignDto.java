package com.comcast.advertisement.campaign;

import com.comcast.advertisement.controller.AdCampaignCreateRequest;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
public class CampaignDto {
    private final String campainContent;
    private final String campainTitle;
    private final Integer expirationDateInSeconds;
    private final String adStatus;

    protected CampaignDto(String title, String content, Integer expiration, String status){
        this.campainTitle = title;
        this.campainContent = content;
        this.expirationDateInSeconds = expiration;
        this.adStatus = status;
    }

    public static CampaignDto fromRequest(AdCampaignCreateRequest request) {
        return new CampaignDto(request.getAdTitle(), request.getAdContent(),request.getDuration(), request.getAdStatus());
    }

    public String getAdStatus() {
        return adStatus;
    }

    public String getCampainContent() {
        return campainContent;
    }

    public String getCampainTitle() {
        return campainTitle;
    }

    public Integer getExpirationDateInSeconds() {
        return Integer.valueOf(expirationDateInSeconds);
    }
}
