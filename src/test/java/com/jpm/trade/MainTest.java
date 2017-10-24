package com.jpm.trade;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rabia on 24/10/2017.
 */
public class MainTest {

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
       // assertEquals(true,Main.exited);

    }
}
