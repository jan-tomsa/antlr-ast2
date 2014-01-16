package com.ness.astdemo.main;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PLSQLRunnerTest {
	private PLSQLRunner runner;

	@Before
	public void setUp() throws Exception {
		runner = new PLSQLRunner("begin\ndbms_output.put_line('a');\nend;");
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
		assertEquals(expected,output);
	}
}
