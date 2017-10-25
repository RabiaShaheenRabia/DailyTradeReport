package com.jpm.trade.process;

import com.jpm.trade.reader.FileReader;
import com.jpm.trade.reader.IFileReader;
import com.jpm.trade.result.ResultSet;

import java.nio.MappedByteBuffer;

/**
 * Created by Rabia on 08/10/2017.
 */
public class DailyTradingProcess implements  IDailyTradingProcess{

    private static IFileReader fileReader = new FileReader();
    private static IReportGenerator reportGenerator = new ReportGenerator();
    private static ResultSet resultSet = new ResultSet();

    public ResultSet dailyTradingprocess(String fileName) {
        MappedByteBuffer mappedByteBuffer =fileReader.processTradingFile(fileName);
        if (mappedByteBuffer!=null) {
            resultSet = reportGenerator.processByteBuffer(mappedByteBuffer);
            if (!resultSet.getFileFormat().trim().isEmpty())
                return resultSet;
            System.out.println("\n|------ Incoming Aggregate Amount On Each SettlementDate------------|\n");
            System.out.println(resultSet.getIncomingAggregateAmount());

            System.out.println("\n|------ Outgoing Aggregate Amount On Each SettlementDate -----------|\n");
            System.out.println(resultSet.getOutgoingAggregateAmount());
            System.out.println("\n|------ Ranking For Incoming ---------------------------------------|\n");
            resultSet.getRankingForIncoming();
            System.out.println("\n|------ Ranking For Outgoing ---------------------------------------|\n");
            resultSet.getRankingForOutgoing();
        }
        return resultSet;
    }


}
