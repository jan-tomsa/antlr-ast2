package com.ness.sourcetransformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.sourcecodeparser.SourceCodeParser;

public class SourceCodeTransformerTest {

	private SourceCodeTransformer transformer;

	@Before
	public void setUp() {
		transformer = new JavaSourceCodeTransformer();
	}

	@Test
	public void testTransformADTToJava() throws Exception {
		String sourceCode = "create or replace type CUSTOM_ADT as object\n" +
							"(\n" +
							"ID NUMBER,\n" +
							"NAME VARCHAR2(255)\n" +
							") not final;\n" +
							"/\n";
		String output = transformer.transformCode(sourceCode);
		String expectedOutput = "xx";
		assertEquals(expectedOutput,output);
	}

	private class JavaSourceCodeTransformer implements SourceCodeTransformer {
		private SourceCodeParser sourceCodeParser;

		@Override
		public String transformCode(String sourceCode) {
			sourceCodeParser = new SourceCodeParser();
			PlSqlElementList elements = sourceCodeParser.parseSource(sourceCode);
			assertNotNull(elements);
			return "null";
		}
	}
}
