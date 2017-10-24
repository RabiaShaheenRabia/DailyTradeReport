package com.jpm.trade;

import com.jpm.trade.process.DailyTradingProcess;
import com.jpm.trade.result.ResultSet;

import static java.lang.System.exit;


/**
 * Created by Rabia on 08/10/2017.
 */
public class Main {


    private static String fileName="C:\\projectsIntellij\\trade-input.txt";
    private static DailyTradingProcess tradingProcess = new DailyTradingProcess();
    private static ResultSet r;

    public static boolean exited = false;

    public static void main(String[] args) {
        fileName = args[0];
        if(!fileName.isEmpty())
        { r=tradingProcess.dailyTradingprocess(fileName);
            if(!r.getFileFormat().isEmpty())
                exit(-1);
        }
        exited=true;
        exit(0);
    }


}
