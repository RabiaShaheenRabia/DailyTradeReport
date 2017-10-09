package com.jpm.trade;

import com.jpm.trade.process.DailyTradingProcessTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Rabia on 08/10/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DailyTradingProcessTest.class, //test case 1
        //ReportGeneratorTest.class     //test case 2
})
public class TestSuite {
}
