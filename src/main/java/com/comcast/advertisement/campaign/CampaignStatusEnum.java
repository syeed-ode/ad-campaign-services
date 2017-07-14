package com.comcast.advertisement.campaign;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
public enum CampaignStatusEnum {
      ACTIVE("Active")
    , INACTIVE("Inactive");

    private String value;

    CampaignStatusEnum(String name) {
        value = name;
    }

    public static CampaignStatusEnum from(String string){

        return Arrays.stream(CampaignStatusEnum.values())
                .filter(s -> s.valueOf().equalsIgnoreCase(string))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("value not CampaignStatusEnum"));
    }

    public String valueOf(){
        return this.value;
    }
}
