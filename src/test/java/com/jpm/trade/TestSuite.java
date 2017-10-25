package com.jpm.trade;

import com.jpm.trade.exception.DailyTradeReportExceptionTest;
import com.jpm.trade.process.DailyTradingProcessTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Rabia on 08/10/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DailyTradingProcessTest.class, //test case 1
        MainTest .class,   //test case 2
        DailyTradeReportExceptionTest.class
})
public class TestSuite {
}
