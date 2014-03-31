package com.ness.sourcetransformer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ness.sourcetransformer.java.JavaSourceCodeTransformer;

public class JavaSourceCodeTransformerTest {

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
								"\n" +
								"\tpublic BigDecimal getId() {\n" +
								"\t\treturn id;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setId(BigDecimal id) {\n" +
								"\t\tthis.id = id;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic String getName() {\n" +
								"\t\treturn name;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setName(String name) {\n" +
								"\t\tthis.name = name;\n" +
								"\t}\n" +
								"\n" +
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
								"\n" +
								"\tpublic BigDecimal getKod() {\n" +
								"\t\treturn kod;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setKod(BigDecimal kod) {\n" +
								"\t\tthis.kod = kod;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic String getNazev() {\n" +
								"\t\treturn nazev;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setNazev(String nazev) {\n" +
								"\t\tthis.nazev = nazev;\n" +
								"\t}\n" +
								"\n" +
								"}\n";
		assertEquals(expectedOutput,output);
	}

	@Test
	public void testTransformRealLifeADTToJava_1() {
		String sourceCode = "CREATE OR REPLACE TYPE SOME_REAL_LIFE_ADT_1_XXX AS OBJECT\n" +
							"(id varchar2(30), \n" +
							" name varchar2(255), \n" +
							" short_name varchar2(50), \n" +
							" description varchar2(255), \n" +
							" some_other_attr varchar2(30),\n" +
							" \n" +
							"\n" +
							"\n" +
							"--constructor without param\n" +
							"CONSTRUCTOR FUNCTION SOME_REAL_LIFE_ADT_1_XXX(SELF IN OUT SOME_REAL_LIFE_ADT_1_XXX) RETURN SELF AS RESULT\n" +
							"PARALLEL_ENABLE\n" +
							"\n" +
							")  NOT FINAL;\n" +
							"/\n";
		String output = transformer.transformCode(sourceCode);
		String expectedOutput = "/** SOME_REAL_LIFE_ADT_1_XXX\n" +
								"*/\n" +
								"public class SomeRealLifeAdt1Xxx {\n" +
								"\tprotected String id;\n" +
								"\tprotected String name;\n" +
								"\tprotected String shortName;\n" +
								"\tprotected String description;\n" +
								"\tprotected String someOtherAttr;\n" +
								"\n" +
								"\tpublic String getId() {\n" +
								"\t\treturn id;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setId(String id) {\n" +
								"\t\tthis.id = id;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic String getName() {\n" +
								"\t\treturn name;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setName(String name) {\n" +
								"\t\tthis.name = name;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic String getShortName() {\n" +
								"\t\treturn shortName;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setShortName(String shortName) {\n" +
								"\t\tthis.shortName = shortName;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic String getDescription() {\n" +
								"\t\treturn description;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setDescription(String description) {\n" +
								"\t\tthis.description = description;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic String getSomeOtherAttr() {\n" +
								"\t\treturn someOtherAttr;\n" +
								"\t}\n" +
								"\n" +
								"\tpublic void setSomeOtherAttr(String someOtherAttr) {\n" +
								"\t\tthis.someOtherAttr = someOtherAttr;\n" +
								"\t}\n" +
								"\n" +
								"}\n";
		assertEquals(expectedOutput,output);
	}
}
