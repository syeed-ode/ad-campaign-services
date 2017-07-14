package com.comcast.advertisement.campaign;

import com.comcast.advertisement.controller.AdCampaignCreateRequest;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
public class CampaignBuilder {
    private String compaignUuid;
    private String campaignContent;
    private String campaignTitle;
    private CampaignStatusEnum campaignStatus;

    public CampaignBuilder campaignContent(String content) {
        campaignContent = content;
        return this;
    }

    public CampaignBuilder campaignTitle(String title) {
        campaignContent = title;
        return this;
    }

    public CampaignBuilder campaignStatus(CampaignStatusEnum status) {
        campaignStatus = status;
        return this;
    }

    public CampaignBuilder compaignUuid(String uuid){
        compaignUuid = uuid;
        return  this;
    }

    public static CampaignEntity build(AdCampaignCreateRequest request, String uuid, CampaignStatusEnum status) {
        CampaignBuilder builder = new CampaignBuilder();
        builder.campaignContent(request.getAdContent())
                .compaignUuid(uuid)
                .campaignStatus(status)
                .campaignTitle(request.getAdTitle());
        return new CampaignEntity(builder, uuid);
    }
}
