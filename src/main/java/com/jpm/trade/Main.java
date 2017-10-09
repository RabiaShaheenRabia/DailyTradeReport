package com.jpm.trade;

import com.jpm.trade.process.DailyTradingProcess;

import static java.lang.System.exit;


/**
 * Created by Rabia on 08/10/2017.
 */
public class Main {


    private static String fileName="C:\\projectsIntellij\\trade-input.txt";
    private static DailyTradingProcess tradingProcess = new DailyTradingProcess();

    public static void main(String[] args) {

        fileName = args[0];
        System.out.println(fileName);
        //tradingProcess.dailyTradingprocess(System.getProperty("path"));

        if(!fileName.isEmpty())
            tradingProcess.dailyTradingprocess(fileName);
        exit(-1);
    }


}
