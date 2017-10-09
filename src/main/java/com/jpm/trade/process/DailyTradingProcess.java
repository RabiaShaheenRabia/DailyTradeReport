package com.jpm.trade.process;

import com.jpm.trade.reader.FileReader;
import com.jpm.trade.result.ResultSet;

import java.nio.MappedByteBuffer;

/**
 * Created by Rabia on 08/10/2017.
 */
public class DailyTradingProcess {

    private static FileReader fileReader = new FileReader();
    private static ReportGenerator reportGenerator = new ReportGenerator();
    private static ResultSet resultSet;

    public static ResultSet dailyTradingprocess(String fileName) {
        MappedByteBuffer mappedByteBuffer =fileReader.processTradingFile(fileName);
        resultSet = reportGenerator.processByteBuffer(mappedByteBuffer);
        System.out.println( "\n|------ Incoming Aggregate Amount On Each SettlementDate------------|\n");
        System.out.println(resultSet.getIncomingAggregateAmount());

        System.out.println( "\n|------ Outgoing Aggregate Amount On Each SettlementDate -----------|\n");
        System.out.println(resultSet.getOutgoingAggregateAmount());
        System.out.println( "\n|------ Ranking For Incoming ---------------------------------------|\n");
        resultSet.getRankingForIncoming();
        System.out.println( "\n|------ Ranking For Outgoing ---------------------------------------|\n");
        resultSet.getRankingForOutgoing();
        return resultSet;
    }


}
