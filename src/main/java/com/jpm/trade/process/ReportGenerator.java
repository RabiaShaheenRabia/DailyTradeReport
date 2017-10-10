package com.jpm.trade.process;

import com.jpm.trade.model.Client;
import com.jpm.trade.result.ResultSet;

import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by Rabia on 08/10/2017.
 */
public class ReportGenerator {

    public ResultSet processByteBuffer(MappedByteBuffer mappedByteBuffer) {
        String content;
        ResultSet resultSet = new ResultSet();
        String[] clients = null;
        if (mappedByteBuffer != null) {
            content = StandardCharsets.UTF_8.decode(mappedByteBuffer).toString();
            if (content.contains("\n")) {
                clients = content.split("\n");
                if(!clients[0].contains("Entity,Buy/Sell,AgreedFx,Currency,InstructionDate,SettlementDate,Units,PricePerUnit")){
                    resultSet.setFileFormat("Please follow File Format");
                    System.out.println("Please follow File Format");
                    return resultSet;}
                resultSet = populateClients(clients);
            }
        }
        return resultSet;
    }

    public ResultSet  populateClients (String[] clients){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        //HashMap<String, Client> set = new HashMap<String, Client>();
        List<Client> list = new ArrayList<Client>();
        Client client = null;
        for(int i=0 ; i<clients.length-1;i++ ){
            String[] properties =  clients[i+1].split(",");
            //convert String to LocalDate
            LocalDate instructionDate = LocalDate.parse(properties[4], formatter);
            LocalDate settlementDate = LocalDate.parse(properties[5], formatter);
            String currency = properties[3];
            settlementDate=adjustSettlementDate(currency,settlementDate);
            //USD amount of a trade = Price per unit * Units * Agreed Fx
            BigDecimal amount = BigDecimal.valueOf(ComputeAmount(properties));
            client = new Client (properties[0],properties[1], BigDecimal.valueOf(new Double(properties[2])),
                    properties[3],instructionDate,settlementDate,
                    new Integer(properties[6]),BigDecimal.valueOf(new Double(properties[7])), amount);
            //set.put(client.getEntity(), client);
            list.add(client);
        }

        ResultSet resultSet =computeResultSet(list);
        return resultSet ;
    }

    private ResultSet computeResultSet(List<Client> list) {

        ResultSet resultSet = new ResultSet();

        Map<LocalDate, BigDecimal> outgoingAggregateAmount = aggregateAmountOnSettlementDate(getFilterOutput(list, "B"));
        Map<LocalDate, BigDecimal> incomingAggregateAmount = aggregateAmountOnSettlementDate(getFilterOutput(list, "S"));

        resultSet.setOutgoingAggregateAmount(outgoingAggregateAmount);
        resultSet.setIncomingAggregateAmount(incomingAggregateAmount);

        // RankingOn both resultB and resultS daily basis
        Map<LocalDate,Supplier<Stream<Client>>> rankingForOutgoing =ranking(getFilterOutput(list, "B"));
        Map<LocalDate,Supplier<Stream<Client>>> rankingForIncoming =ranking(getFilterOutput(list, "S"));

        resultSet.setRankingForOutgoing(rankingForOutgoing);
        resultSet.setRankingForIncoming(rankingForIncoming);

        return resultSet;
    }

    private Map<LocalDate,Supplier<Stream<Client>>> ranking(List<Client> result) {

        Map<LocalDate,List<Client>> map=result.stream()
                .collect(groupingBy(Client::getSettlementDate));
        Map<LocalDate,Supplier<Stream<Client>>> clientMap=new HashMap<>();

        Iterator<LocalDate> groupingByDateAndTrade = map.keySet().iterator();
        while(groupingByDateAndTrade.hasNext()) {
            LocalDate date = groupingByDateAndTrade.next();
            List<Client> clientList = map.get(date);

            Supplier<Stream<Client>> clientStream = () ->clientList
                    .stream().sorted((c1, c2) -> c2.getAmount().compareTo(c1.getAmount()));

             clientMap.put(date,clientStream);

        }
        return clientMap;
    }


    private Map<LocalDate, BigDecimal> aggregateAmountOnSettlementDate(List<Client> result) {
        return result.stream()
                   .collect(groupingBy(Client::getSettlementDate,
                            mapping(Client::getAmount,
                                    reducing(BigDecimal.ZERO, BigDecimal::add))));
    }


    private List<Client> getFilterOutput(List<Client> list, String filter) {
        List<Client> result =  new ArrayList<Client>();
        for (Client c : list) {
            if (filter.equals(c.getTrade())) {
                result.add(c);
            }
        }
        return result;
    }

    private Double ComputeAmount(String[] properties) {
        return (new Double(properties[2]) *new Integer(properties[6]) * new Double(properties[7]) ) ;
    }

    private LocalDate adjustSettlementDate(String currency,LocalDate settlementDate){
        String dayOfWeek = settlementDate.getDayOfWeek().name().toUpperCase();

        if (currency.equalsIgnoreCase("SAR") || currency.equalsIgnoreCase("AED") ) {
            //Using DayOfWeek to execute dependent business logic
            switch (dayOfWeek) {
                case "FRIDAY":
                    settlementDate = settlementDate.plusDays(2);
                    break;
                case "SATURDAY":
                    settlementDate = settlementDate.plusDays(1);
                    break;
                default:
                    break;
            }
        }
        else
            switch (dayOfWeek) {
                case "SATURDAY":
                    settlementDate = settlementDate.plusDays(2);
                    break;
                case "SUNDAY":
                    settlementDate = settlementDate.plusDays(1);
                    break;
                default:
                    break;
            }
        return settlementDate;
    }
}
