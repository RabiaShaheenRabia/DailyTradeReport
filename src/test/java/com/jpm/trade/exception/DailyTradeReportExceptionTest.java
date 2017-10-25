package com.jpm.trade.exception;

import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Rabia on 25/10/2017.
 */
public class DailyTradeReportExceptionTest {

    @Test
    public void testExceptionMessage() {
        try {

            throw new DailyTradeReportException("Exception Gen");
        } catch (DailyTradeReportException exc) {
            assertEquals(exc.getMessage(), "Exception Gen");
        }
    }
}
