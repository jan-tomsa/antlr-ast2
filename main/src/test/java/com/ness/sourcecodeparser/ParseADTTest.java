package com.ness.sourcecodeparser;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;

public class ParseADTTest {

	private SourceCodeParser sourceCodeParser;

	@Before
	public void setUp() {
		sourceCodeParser = new SourceCodeParser();
	}

	@Test
	public void parseADTTest() {
		PlSqlElementList adt = sourceCodeParser.parseSource("create or replace type CUSTOM_ADT as object\n" +
				"(\n" +
				"ID NUMBER,\n" +
				"NAME VARCHAR2(255)\n" +
				") not final;\n" +
				"/\n");
		assertNotNull(adt);
		assertEquals(1,adt.size());
		assertTrue(adt.getElement(0) instanceof PlSqlTypeDeclaration);
		PlSqlTypeDeclaration x = (PlSqlTypeDeclaration) adt.getElement(0);
	}
}
