package com.comcast.advertisement.campaign;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
public class CampaingDtoBuilder {
    private String content;
    private String title;
    private Integer expirationInSeconds;
    private String status;

    public CampaingDtoBuilder content(String data){
        this.content = data;
        return this;
    }

    public CampaingDtoBuilder title(String data){
        this.title = data;
        return this;
    }

    public CampaingDtoBuilder expiration(Integer data){
        this.expirationInSeconds = data;
        return this;
    }

    public CampaingDtoBuilder status(String data){
        this.status = data;
        return this;
    }

    public CampaignDto build(){
        return new CampaignDto(this.title,this.content, this.expirationInSeconds, this.status);
    }
}
