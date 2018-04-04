package com.owe.wordladder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Vector;


/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }


    public void teststart() {
        WordLadder wd = new WordLadder("nice", "ok");
        assertEquals("nice", wd.getS());

    }

    public void testend() {
        WordLadder wd = new WordLadder("happy", "string");
        assertEquals("string", wd.getE());

    }

    public void testresult() {
        WordLadder wd = new WordLadder("nice", "ok");
        Vector<String> result = wd.BFS();
        int length = result.size();
        assertEquals("nice", result.get(length - 1));
        assertEquals("ok", result.get(0));
    }

    public void testnoresult() {
        WordLadder wd = new WordLadder("sadfhwefnonasodncwef", "osdacqwehcqwecqwck");
        Vector<String> result = wd.BFS();

        assertEquals(null, result);
    }

}
