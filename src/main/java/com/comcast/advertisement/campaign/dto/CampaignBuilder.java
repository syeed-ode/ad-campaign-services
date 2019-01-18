package com.comcast.advertisement.campaign.dto;

import com.comcast.advertisement.campaign.CampaignDto;
import com.comcast.advertisement.campaign.CampaignStatusEnum;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.from;

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
    private Integer expirationDate;

    public CampaignBuilder campaignContent(String content) {
        campaignContent = content;
        return this;
    }

    public CampaignBuilder campaignTitle(String title) {
        campaignTitle = title;
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

    public CampaignBuilder expirationDate(Integer expirationDate){
        this.expirationDate = expirationDate;
        return  this;
    }

    private CampaignBuilder() {

    }

    public static CampaignEntity build(final CampaignDto dto, final String uuid) {
        CampaignBuilder builder = new CampaignBuilder();
        builder.campaignContent(dto.getCampainContent())
                .campaignTitle(dto.getCampainTitle())
                .campaignStatus(from(dto.getAdStatus()))
                .compaignUuid(uuid)
                .expirationDate(dto.getExpirationDateInSeconds());
        return new CampaignEntity(builder);
    }

    public String getCompaignUuid() {
        return compaignUuid;
    }

    public String getCampaignContent() {
        return campaignContent;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public CampaignStatusEnum getCampaignStatus() {
        return campaignStatus;
    }

    public Integer getExpirationDate() {
        return expirationDate;
    }
}
