package com.comcast.advertisement.campaign;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.ACTIVE;
import static com.comcast.advertisement.campaign.CampaignStatusEnum.from;
import static org.junit.Assert.*;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@RunWith(MockitoJUnitRunner.class)
public class CampaignStatusEnumTest {

    @Test
    public void from_Success(){
        CampaignStatusEnum result = from(ACTIVE.valueOf());
        Assert.assertNotNull(result);
    }

    @Test(expected = NoSuchElementException.class)
    public void form_whenWrongEnumValue_throwNoSuchElementException() {
        CampaignStatusEnum result = from("False data");
    }
}