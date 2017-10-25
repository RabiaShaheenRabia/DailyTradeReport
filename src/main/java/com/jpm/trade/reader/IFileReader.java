package com.jpm.trade.reader;

import java.nio.MappedByteBuffer;

/**
 * Created by Rabia on 25/10/2017.
 */
public interface IFileReader {
    public MappedByteBuffer processTradingFile(String fileName);
}
