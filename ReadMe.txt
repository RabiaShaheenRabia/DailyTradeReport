
DailyTradeReport - Gradle Application for report generation on settlementDate basis for buying and selling trades
Assigning - Ranking on both selling and buying products
Constraint - Each trade name Must be unique/settlementDate
Application built and tested on Windows O/S

 Amount in USD settled incoming everyday
 Amount in USD settled outgoing everyday
 Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing

To execute properly please make sure to build project, please place input file of following format

Entity,Buy/Sell,AgreedFx,Currency,InstructionDate,SettlementDate,Units,PricePerUnit
Client_10,S,3.0,SAR,11 Jan 2016,13 Jan 2016,1000,100.25
Client_11,S,3.0,SED,11 Jan 2016,13 Jan 2016,1001,100.25
Client_12,S,3.0,AED,11 Jan 2016,14 Jan 2016,1002,100.25
Client_13,S,3.0,USA,11 Jan 2016,14 Jan 2016,1003,100.25
Client_101,B,3.0,SAR,11 Jan 2016,13 Jan 2016,1000,100.25
Client_111,B,3.0,SED,11 Jan 2016,13 Jan 2016,1001,100.25
Client_121,B,3.0,AED,11 Jan 2016,16 Jan 2016,1002,100.25
Client_131,B,3.0,USA,11 Jan 2016,16 Jan 2016,1003,100.25

For execution following is command for CLI
java -cp DailyTradingProcess.jar com.jpm.trade.Main C:\\projectsIntellij\\trade-input.txt

Upon successful execution, output will be displayed in following format.


|------ Incoming Aggregate Amount On Each SettlementDate------------|

{2016-01-14=603003.75, 2016-01-13=601800.75}

|------ Outgoing Aggregate Amount On Each SettlementDate -----------|

{2016-01-13=601800.75, 2016-01-18=301652.25, 2016-01-17=301351.5}

|------ Ranking For Incoming ---------------------------------------|

SettlementDate Entity Currency TradeAmount Rank
2016-01-14  Client_13  USA     301652.25     1
2016-01-14  Client_12  AED     301351.5     2
2016-01-13  Client_11  SED     301050.75     1
2016-01-13  Client_10  SAR     300750.0     2

|------ Ranking For Outgoing ---------------------------------------|

SettlementDate Entity Currency TradeAmount Rank
2016-01-13  Client_111  SED    301050.75     1
2016-01-13  Client_101  SAR    300750.0     2
2016-01-18  Client_131  USA    301652.25     1
2016-01-17  Client_121  AED    301351.5     1

FileGenerator - Creates i/p file.
Main is main class. initiates all process.

To execute test cases, Please write following command
gradlew test --tests com.jpm.trade.TestSuite