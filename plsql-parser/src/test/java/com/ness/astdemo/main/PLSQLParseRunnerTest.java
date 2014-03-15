package com.ness.astdemo.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PLSQLParseRunnerTest {

	@Test
	public void testSimpleBlock() throws Exception {
		PLSQLParseRunner runner = new PLSQLParseRunner("begin\n"
				+ "dbms_output.put_line('a');\n"
				+ "end;\n/");
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

	@Test
	public void testBlockWithDeclare() throws Exception {
		PLSQLParseRunner runner = new PLSQLParseRunner("declare x number;\nbegin null; end;");
		String output = runner.execute();
		String expected = "null\n" +
				"\n" +
				"  declare\n" +
				"\n" +
				"  x\n" +
				"\n" +
				"  number\n";
		assertEquals(expected,output);
	}
}
