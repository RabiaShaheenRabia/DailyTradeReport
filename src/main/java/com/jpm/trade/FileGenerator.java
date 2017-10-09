package com.jpm.trade;

import com.jpm.trade.model.Client;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Rabia on 08/10/2017.
 */
public class FileGenerator {

    public  void main(String[] args){
        PrintWriter writer = null ;

        try {
            writer = new PrintWriter("src/main/resources/trade-input.txt", "UTF-8");
        }
        catch (FileNotFoundException exc){
            System.out.println(exc.getMessage());
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }

        Client C_B = null;
        Client C_S = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");


        LocalDate instructionDate =  LocalDate.parse("01 Jan 2016", formatter);
        LocalDate settlementDate =  LocalDate.parse("03 Jan 2016", formatter);


        int count = 10;

        writer.println("Entity,Buy/Sell,AgreedFx,Currency,InstructionDate,SettlementDate,Units,PricePerUnit");

        while(count>0) {
            C_B = new Client("Client_"+count , "B", BigDecimal.valueOf(0.50d + count*0.25d),
                    "SGP", instructionDate.plusDays(count),
                    settlementDate.plusDays(count),
                    100 * count, BigDecimal.valueOf(100.25d)) ;


            C_S = new Client("Client_"+count+"_"+count, "S",BigDecimal.valueOf(0.50d + count*0.25d),
                    "SGP", instructionDate.plusDays(count),
                    settlementDate.plusDays(count),
                    100 * count, BigDecimal.valueOf(100.25d)) ;


            if (count%2==0)
                writer.println(C_B.toString().replace("SGP", "SAR"));
            else
                writer.println(C_B.toString());

            writer.println(C_S.toString());

            count -- ;
        }

        writer.close();
    }
}
