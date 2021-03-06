package com.jpm.trade;

import com.jpm.trade.process.DailyTradingProcess;
import com.jpm.trade.process.IDailyTradingProcess;
import com.jpm.trade.result.ResultSet;

import java.io.File;

import static java.lang.System.exit;


/**
 * Created by Rabia on 08/10/2017.
 */
public class Main {


    private static String fileName="C:\\projectsIntellij\\trade-input.txt";
    private static IDailyTradingProcess tradingProcess = new DailyTradingProcess();
    private static ResultSet r;

    public static void main(String[] args) {
        fileName = args[0];
        File f = new File(fileName);
        if(!fileName.isEmpty() && f.exists() && !f.isDirectory())
        { r=tradingProcess.dailyTradingprocess(fileName);
            if(!r.getFileFormat().isEmpty())
                exit(-1);
        }
        exit(0);
    }


}
