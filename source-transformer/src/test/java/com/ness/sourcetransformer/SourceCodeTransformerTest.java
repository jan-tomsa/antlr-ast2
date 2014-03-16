package com.ness.sourcetransformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;
import com.ness.sourcecodeparser.SourceCodeParser;
import com.ness.sourcetransformer.java.PlSql2Java;

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
		String expectedOutput = "/** CUSTOM_ADT\n" +
								"*/\n" +
								"public class CustomAdt {\n" +
								"}\n";
		assertEquals(expectedOutput,output);
	}

	@Test
	public void testTransformADT2ToJava() throws Exception {
		String sourceCode = "create or replace type CUSTOM_ADT2 as object\n" +
							"(\n" +
							"ID NUMBER,\n" +
							"NAME VARCHAR2(255)\n" +
							") not final;\n" +
							"/\n";
		String output = transformer.transformCode(sourceCode);
		String expectedOutput = "/** CUSTOM_ADT2\n" +
								"*/\n" +
								"public class CustomAdt2 {\n" +
								"}\n";
		assertEquals(expectedOutput,output);
	}

	private class JavaSourceCodeTransformer implements SourceCodeTransformer {
		private SourceCodeParser sourceCodeParser;

		@Override
		public String transformCode(String sourceCode) {
			sourceCodeParser = new SourceCodeParser();
			PlSqlElementList elements = sourceCodeParser.parseSource(sourceCode);
			PlSqlTypeDeclaration el1 = (PlSqlTypeDeclaration) elements.getElement(0);
			assertNotNull(elements);
			String className = generateClassName(el1.getName());
			return generateClassJavaDoc(el1) +
					"public class " + className + " {\n" +
					"}\n";
		}

		private String generateClassName(String adtName) {
			return PlSql2Java.transformIdentifier(adtName);
		}

		private String generateClassJavaDoc(PlSqlTypeDeclaration el1) {
			return "/** " + el1.getName() + "\n" +
					"*/\n";
		}
	}
}
