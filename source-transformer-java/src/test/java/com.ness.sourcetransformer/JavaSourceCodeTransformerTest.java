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


}
