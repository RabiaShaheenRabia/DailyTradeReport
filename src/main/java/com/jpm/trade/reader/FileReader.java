package com.jpm.trade.reader;

import java.io.IOException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.EnumSet;


/**
 * Created by Rabia on 08/10/2017.
 */
public class FileReader {

   String  getFileURIFromResources(String fileName)  {
       ClassLoader classLoader = getClass().getClassLoader();
       String path= classLoader.getResource(fileName).getPath().replace("/C:/","C:/");
       // String path1 = classLoader.getResource("/resources/" + fileName).getPath();
       //path = fileName;

       //URL url = getClass().getResource(fileName);
       return path;
    }

    public MappedByteBuffer processTradingFile(String fileName) {

        Path pathToRead = null;
        try {
             pathToRead = Paths.get(fileName);
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }
        String s = null;
        MappedByteBuffer mappedByteBuffer = null;
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                pathToRead, EnumSet.of(StandardOpenOption.READ))) {

            mappedByteBuffer = fileChannel
                    .map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

        }  catch (IOException exc) {
            System.out.print(exc.getMessage());
        } catch (Exception exc) {
            System.out.print(exc.getMessage());
        }
        return mappedByteBuffer;
    }


}
