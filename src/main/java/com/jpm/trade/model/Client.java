package com.jpm.trade.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Rabia on 08/10/2017.
 */

public class Client {

    private String entity = "";
    private String trade = ""; //B - Buy / S - Sell
    private BigDecimal agreedFx =BigDecimal.valueOf(0);
    private String currency = "";
    private LocalDate instructionDate ;
    private LocalDate settlementDate;
    private int units =0;
    private BigDecimal pricePerUnit=BigDecimal.valueOf(0);
    private BigDecimal amount =BigDecimal.valueOf(0);

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank=0;

    public Client() {
    }


    // --- For Process Trading Clients ---//
    public Client(String entity, String trade, BigDecimal agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units, BigDecimal pricePerUnit, BigDecimal amount) {
        this.entity = entity;
        this.trade = trade;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public String getEntity() {
        return entity;
    }

    public String getTrade() {
        return trade;
    }


    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    // --- For File Generation ---//
    public Client(String entity, String trade, BigDecimal agreedFx, String currency, LocalDate instructionDate, LocalDate settlementDate, int units, BigDecimal pricePerUnit) {
        this.entity = entity;
        this.trade = trade;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return entity + ',' + trade + ',' + agreedFx +',' + currency + ',' + formatter.format(instructionDate) +',' + formatter.format(settlementDate) +"," + units +"," + pricePerUnit ;
    }

}
