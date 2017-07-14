package com.comcast.advertisement.controller;

import com.comcast.advertisement.campaign.CampaignEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @JsonProperty("duration")
    private final String duration;

    @JsonProperty("ad_content")
    private final String adContent;

    @JsonProperty("ad_title")
    private final String adTitle;

    @JsonProperty("ad_status")
    private final String adStatus;

    private AdCampaignResponse(String externalPartnerId, String duration, String adContent, String adTitle, String adStatus) {
        this.externalPartnerId = externalPartnerId;
        this.duration = duration;
        this.adContent = adContent;
        this.adTitle = adTitle;
        this.adStatus = adStatus;
    }

    public static AdCampaignResponse from(CampaignEntity entity){
        String partnerId = Optional
                .of(entity.getPartnerEntity())
                .map(e -> e.getExternalId())
                .orElse("No partner for this campaign");
        String expirationDate =
                Optional.of(entity.getExpirationDate() * 1000)
                        .map(dateInSeconds -> new Date(dateInSeconds))
                        .map(d -> {
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                            return df.format(d);
                        }).orElse("Expired for more than a day");
        return new AdCampaignResponse(partnerId
                , expirationDate
                , entity.getCampaignContent()
                , entity.getCampaignTitle()
                , entity.getCampaignStatus().valueOf());
    }
}
