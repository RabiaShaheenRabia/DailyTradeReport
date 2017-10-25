package com.jpm.trade.process;

import com.jpm.trade.model.Client;
import com.jpm.trade.result.ResultSet;

import java.nio.MappedByteBuffer;
import java.util.List;

/**
 * Created by Rabia on 25/10/2017.
 */
public interface IReportGenerator {
    ResultSet processByteBuffer(MappedByteBuffer mappedByteBuffer);
    ResultSet populateClients (String[] clients);

}
