package com.comcast.advertisement.utilities;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Service
public class DateUtility {
    public Integer getTimeInEpoch(){
        Date date = new Date();
        Long dateInSeconds = date.getTime() / 1000;
        return dateInSeconds.intValue();
    }
}
