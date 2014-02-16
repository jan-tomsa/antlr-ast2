package com.ness.sourcecodeparser;

import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;

public class ParseADTTest {

	private SourceCodeParser sourceCodeParser;

	@Before
	public void setUp() {
		sourceCodeParser = new SourceCodeParser();
	}

	@Test
	public void parseADTTest() {
		PlSqlElementList elements = sourceCodeParser.parseSource("create or replace type CUSTOM_ADT as object\n" +
				"(\n" +
				"ID NUMBER,\n" +
				"NAME VARCHAR2(255)\n" +
				") not final;\n" +
				"/\n");
		assertNotNull(elements);
		assertEquals(1, elements.size());
		assertTrue(elements.getElement(0) instanceof PlSqlTypeDeclaration);
		PlSqlTypeDeclaration adt = (PlSqlTypeDeclaration) elements.getElement(0);
		assertEquals("CUSTOM_ADT",adt.getName());
		List<PlSqlTypeAttribute> attributes = adt.getAttributes();
		assertNotNull(attributes);
		//assertEquals(2,attributes.size());
	}
}
