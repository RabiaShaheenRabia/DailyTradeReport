package com.jpm.trade.result;

import com.jpm.trade.model.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by Rabia on 08/10/2017.
 */
public class ResultSet {

    static Map<LocalDate, BigDecimal> outgoingAggregateAmount ;
    static Map<LocalDate, BigDecimal> incomingAggregateAmount ;

    // RankingOn both resultB and resultS daily basis
    static Map<LocalDate,Supplier<Stream<Client>>> rankingForOutgoing ;
    static Map<LocalDate,Supplier<Stream<Client>>> rankingForIncoming ;


    String fileFormat ="";

    public static Map<LocalDate, BigDecimal>  getOutgoingAggregateAmount() {
        return outgoingAggregateAmount;
    }

    public void setOutgoingAggregateAmount(Map<LocalDate, BigDecimal> outgoingAggregateAmount) {
        this.outgoingAggregateAmount = outgoingAggregateAmount;
    }

    public static Map<LocalDate, BigDecimal> getIncomingAggregateAmount() {
        return incomingAggregateAmount;
    }

    public void setIncomingAggregateAmount(Map<LocalDate, BigDecimal> incomingAggregateAmount) {
        this.incomingAggregateAmount = incomingAggregateAmount;
    }

    public static Map<LocalDate, Supplier<Stream<Client>>> getRankingForOutgoing() {

        if (rankingForOutgoing.size()>0) {

            System.out.println("SettlementDate Entity Currency TradeAmount Rank");
            Iterator<LocalDate> groupingByDateAndTrade = rankingForOutgoing.keySet().iterator();
            while (groupingByDateAndTrade.hasNext()) {
                LocalDate date = groupingByDateAndTrade.next();
                Supplier<Stream<Client>> clientStream = rankingForOutgoing.get(date);
                final AtomicInteger rank = new AtomicInteger(0);

                clientStream.get().forEach(c -> { c.setRank(rank.incrementAndGet());
                    System.out.println(c.getSettlementDate() + "  " + c.getEntity() + "  "+ c.getCurrency()+"    "
                            + c.getAmount() + "     " + c.getRank());
                });
            }
        }
        return rankingForOutgoing;

    }

    public void setRankingForOutgoing(Map<LocalDate,Supplier<Stream<Client>>> rankingForOutgoing) {
        this.rankingForOutgoing = rankingForOutgoing;
    }

    public static Map<LocalDate, Supplier<Stream<Client>>> getRankingForIncoming() {

        if (rankingForIncoming.size()>0) {

            System.out.println("SettlementDate Entity Currency TradeAmount Rank");
            Iterator<LocalDate> groupingByDateAndTrade = rankingForIncoming.keySet().iterator();
            while (groupingByDateAndTrade.hasNext()) {
                LocalDate date = groupingByDateAndTrade.next();
                Supplier<Stream<Client>> clientStream = rankingForIncoming.get(date);
                final AtomicInteger rank = new AtomicInteger(0);
                clientStream .get(). forEach(c -> {
                    System.out.println(c.getSettlementDate() + "  " + c.getEntity() + "  "+ c.getCurrency()+"     "
                            + c.getAmount() + "     " + rank.incrementAndGet());
                });


            }
        }
        return rankingForIncoming;
    }

    public void setRankingForIncoming(Map<LocalDate, Supplier<Stream<Client>>> rankingForIncoming) {
        this.rankingForIncoming = rankingForIncoming;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public  void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}
