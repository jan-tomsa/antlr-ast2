package com.ness.astdemo.main;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private App app;

	@Before
	public void setup() {
		app = new App("1+2+4;");
	}

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
	    String output = app.execute();
	    String expected = "SCRIPT\n" +
			    "SCRIPT\n" +
			    "  7\n" +
			    "    3\n" +
			    "      1\n" +
			    "    3\n" +
			    "      2\n" +
			    "  7\n" +
			    "    4\n";
	    assertEquals(expected,output);
    }
}
