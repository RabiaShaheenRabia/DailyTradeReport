package com.jpm.trade.process;


import com.jpm.trade.dummy.DummyDailyTradingProcess;
import com.jpm.trade.dummy.DummyResultSet;
import com.jpm.trade.model.Client;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rabia on 08/10/2017.
 */

public class DailyTradingProcessTest {

    DummyDailyTradingProcess tradingProcess = new DummyDailyTradingProcess();
    DummyResultSet dummyResultSet =new DummyResultSet();

    LocalDate instructionDate =  LocalDate.of(2016,01,11);
    LocalDate settlementDate =  LocalDate.of(2016,01,13);



    Client client1B;
    Client client2B;

    Client client1S;
    Client client2S;
    Map<LocalDate, Supplier<Stream<Client>>> incoming = new HashMap<>();
    Map<LocalDate, Supplier<Stream<Client>>> outgoing = new HashMap<>();
    Supplier<Stream<Client>> clientStream;
    List<Client> clients;
    @Before
    public void setUp() throws Exception {

        String fileName = "test-trade-input.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        String path= classLoader.getResource(fileName).getPath().replace("/C:/","C:/");


        tradingProcess.dailyTradingprocess(path);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        //Client_10,S,3.0,SAR,11 Jan 2016,13 Jan 2016,1000,100.25
        //Client_11,S,3.0,SED,11 Jan 2016,13 Jan 2016,1001,100.25


        client1B = new Client("Client_11" , "B", BigDecimal.valueOf(3.0),
                "SED", instructionDate,
                settlementDate,
                1000, BigDecimal.valueOf(100.25d),BigDecimal.valueOf(3.0*1001*100.25)) ;
        client2B = new Client("Client_10" , "B", BigDecimal.valueOf(3.0),
                "SAR", instructionDate,
                settlementDate,
                1000, BigDecimal.valueOf(100.25d),BigDecimal.valueOf(3.0*1000*100.25)) ;


        clients = new ArrayList<>();
        client1B.setRank(1);
        clients.add(client1B);
        client2B.setRank(2);
        clients.add(client2B);
        clientStream = () -> clients.stream() ;
        incoming.put(settlementDate, clientStream);
    }

    @After
    public void tearDown() throws Exception {
        dummyResultSet = null;
    }

    @Test
    public void dailyTradingProcessForIncomingAggregateAmount() throws Exception {
       assertEquals("{2016-01-13=601800.75}",DummyResultSet.getIncomingAggregateAmount().toString());
       assertEquals("{2016-01-13=601800.75}",DummyResultSet.getOutgoingAggregateAmount().toString());

       System.out.println("|------ Test Cases Ranking For Incoming ---------------------------------------|");

       int i=0;
       Iterator iterator =DummyResultSet.getRankingForIncoming().get(settlementDate).get().iterator();
       while (iterator.hasNext()) {
           Client client =(Client) iterator.next();

           assertEquals(clients.get(i).getRank(),
                  client.getRank());

           assertEquals(clients.get(i).getAmount(),
                   client.getAmount());

           i++;
       }
    }



}