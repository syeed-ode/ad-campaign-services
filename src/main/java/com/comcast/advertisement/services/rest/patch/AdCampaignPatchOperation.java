package com.comcast.advertisement.services.rest.patch;

import com.comcast.advertisement.campaign.CampaignEntity;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/17/17
 */
@Configuration
public enum AdCampaignPatchOperation {
    AD_CONTENT("ad_content") {
        CampaignEntity operate(String data){
            CampaignEntity campaignEntity = new CampaignEntity();
            campaignEntity.setCampaignContent(data);
            return campaignEntity;
        }
    };

    private String fieldName;

    AdCampaignPatchOperation(String key) {
        fieldName = key;
    }

    public static AdCampaignPatchOperation from(String name) {
        return Arrays.stream(AdCampaignPatchOperation.values())
                .filter(v -> v.toString().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    abstract CampaignEntity operate(String data);
}
