package com.jpm.trade;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Created by Rabia on 24/10/2017.
 */

public class MainTest{

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testMain() {
        System.out.println("main");
        String[] args ={"",""};
        String fileName = "test-trade-input.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        String path= classLoader.getResource(fileName).getPath().replace("/C:/","C:/");
        args[0] = path;
        exit.expectSystemExitWithStatus(0);
        Main.main(args);
    }

    @Test
    public void testMainWrongFileFormat() {
        System.out.println("main");
        String[] args ={"",""};
        String fileName = "test-trade-input1.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        String path= classLoader.getResource(fileName).getPath().replace("/C:/","C:/");
        args[0] = path;
        exit.expectSystemExitWithStatus(-1);
        Main.main(args);
    }

}
