package com.comcast.advertisement.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@RunWith(MockitoJUnitRunner.class)
public class DateUtilityTest {

    DateUtility service = new DateUtility();

    @Test
    public void getTimeInEpoch_Success() {
        Object currentServiceTime = service.getTimeInEpoch();
        assureProperTyping(currentServiceTime);

        Long currentLibraryTimeInMillies = javaLibrayTime();

        Integer currentLibraryTime = currentLibraryTimeInMillies.intValue() / 1000;
        Assert.assertTrue((((Integer)currentServiceTime - currentLibraryTime) >= 0));
    }

    private void assureProperTyping(Object currentServiceTime) {
        Assert.assertTrue(currentServiceTime instanceof Integer);
        Assert.assertFalse(currentServiceTime instanceof Long);
    }

    private Long javaLibrayTime() {
        Date d = new Date();
        return d.getTime();
    }

}