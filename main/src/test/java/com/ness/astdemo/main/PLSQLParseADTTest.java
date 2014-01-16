package com.ness.astdemo.main;

import org.junit.Before;
import org.junit.Test;

public class PLSQLParseADTTest {
	private PLSQLParseRunner runner;

	@Before
	public void setUp() throws Exception {
		runner = new PLSQLParseRunner("create or replace type CUSTOM_ADT as object\n" +
				"(\n" +
				"ID NUMBER,\n" +
				"NAME VARCHAR2(255)\n" +
				") not final;\n" +
				"/\n");
	}

	@Test
	public void testExecute() throws Exception {
		String output = runner.execute();
		String expected = "null\n" +
				"\n" +
				"  begin\n" +
				"\n" +
				"  dbms_output\n" +
				"\n" +
				"  .\n" +
				"\n" +
				"  put_line\n" +
				"\n" +
				"  (\n" +
				"\n" +
				"  'a'\n" +
				"\n" +
				"  )\n";
		System.out.println(output);
		//assertEquals(expected,output);
	}
}
