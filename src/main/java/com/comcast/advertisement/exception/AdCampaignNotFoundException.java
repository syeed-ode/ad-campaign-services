package com.comcast.advertisement.exception;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
public class AdCampaignNotFoundException extends RuntimeException {
    public AdCampaignNotFoundException(String message) {
        super(message);
    }
}
