package com.ness.sourcetransformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlDatatype;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeNumber;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeVarchar2;
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
								"\tprotected BigDecimal id;\n" +
								"\tprotected String name;\n" +
								"}\n";
		assertEquals(expectedOutput,output);
	}

	@Test
	public void testTransformADT2ToJava() throws Exception {
		String sourceCode = "create or replace type CUSTOM_ADT2 as object\n" +
							"(\n" +
							"KOD NUMBER,\n" +
							"NAZEV VARCHAR2(255)\n" +
							") not final;\n" +
							"/\n";
		String output = transformer.transformCode(sourceCode);
		String expectedOutput = "/** CUSTOM_ADT2\n" +
								"*/\n" +
								"public class CustomAdt2 {\n" +
								"\tprotected BigDecimal kod;\n" +
								"\tprotected String nazev;\n" +
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
					generateAttributes(el1) +
					"}\n";
		}

		private String generateAttributes(PlSqlTypeDeclaration adt) {
			final StringBuilder result = new StringBuilder();
			List<PlSqlTypeAttribute> attributes = adt.getAttributes();
			for (PlSqlTypeAttribute attr : attributes) {
				result.append(generateAttribute(attr));
			}
			return result.toString();
		}

		private String generateAttribute(PlSqlTypeAttribute attr) {
			StringBuilder sb = new StringBuilder("\tprotected ");
			PlSqlDatatype dt = attr.getDatatype();
			sb.append(generateDatatype(dt));
			sb.append(" ");
			sb.append(PlSql2Java.transformFieldName(attr.getName()));
			sb.append(";\n");
			return sb.toString();
		}

		private String generateDatatype(PlSqlDatatype dt) {
			if (dt instanceof PlSqlDatatypeVarchar2) {
				return "String";
			}
			if (dt instanceof PlSqlDatatypeNumber) {
				return "BigDecimal";
			}
			return "Unknown";
		}

		private String generateClassName(String adtName) {
			return PlSql2Java.transformClassName(adtName);
		}

		private String generateClassJavaDoc(PlSqlTypeDeclaration el1) {
			return "/** " + el1.getName() + "\n" +
					"*/\n";
		}
	}
}
