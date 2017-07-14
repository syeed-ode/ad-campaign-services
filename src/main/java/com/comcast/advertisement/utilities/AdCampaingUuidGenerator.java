package com.comcast.advertisement.utilities;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Ad Service Application
 * <p>
 *
 *     Utilized to facilitate testing.
 *
 * Author: syeedode
 * Date: 7/13/17
 */
@Service
public class AdCampaingUuidGenerator {
    public String uuid(){
        return UUID.randomUUID().toString();
    }
}
