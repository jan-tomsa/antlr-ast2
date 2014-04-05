package com.ness.sourcecodeparser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeNumber;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeVarchar2;

public class ParseADTTest {

	private SourceCodeParser sourceCodeParser;

	@Before
	public void setUp() {
		sourceCodeParser = new SourceCodeParser();
	}

	@Test
	public void parseADTTest1Parameter() {
		PlSqlElementList elements = sourceCodeParser.parseSource("create or replace type CUSTOM_ADT2 as object\n" +
				"(\n" +
				"ID NUMBER\n" +
				") not final;\n" +
				"/\n");
		assertNotNull(elements);
		assertEquals(1, elements.size());
		assertTrue(elements.getElement(0) instanceof PlSqlTypeDeclaration);
		PlSqlTypeDeclaration adt = (PlSqlTypeDeclaration) elements.getElement(0);
		assertEquals("CUSTOM_ADT2",adt.getName());
		List<PlSqlTypeAttribute> attributes = adt.getAttributes();
		assertNotNull(attributes);
		assertEquals(1,attributes.size());
		PlSqlTypeAttribute attr1 = attributes.get(0);
		assertNotNull(attr1);
		assertEquals("ID",attr1.getName());
		assertTrue(attr1.getDatatype() instanceof PlSqlDatatypeNumber);
	}

	@Test
	public void parseADTTest2Params() {
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
		assertEquals(2,attributes.size());
		PlSqlTypeAttribute attr1 = attributes.get(0);
		assertNotNull(attr1);
		assertEquals("ID", attr1.getName());
		assertTrue(attr1.getDatatype() instanceof PlSqlDatatypeNumber);
		PlSqlTypeAttribute attr2 = attributes.get(1);
		assertNotNull(attr2);
		assertEquals("NAME",attr2.getName());
		assertTrue(attr2.getDatatype() instanceof PlSqlDatatypeVarchar2);
		PlSqlDatatypeVarchar2 attr2Datatype = (PlSqlDatatypeVarchar2)attr2.getDatatype();
		assertEquals(255, attr2Datatype.getSize());
	}

	@Test
	public void parseADTTest3Params() {
		PlSqlElementList elements = sourceCodeParser.parseSource("create or replace type CUSTOM_ADT as object\n" +
				"(\n" +
				"ID NUMBER,\n" +
				"NAME VARCHAR2(255),\n" +
				"LONG_NAME VARCHAR2(2000)\n" +
				") not final;\n" +
				"/\n");
		assertNotNull(elements);
		assertEquals(1, elements.size());
		assertTrue(elements.getElement(0) instanceof PlSqlTypeDeclaration);
		PlSqlTypeDeclaration adt = (PlSqlTypeDeclaration) elements.getElement(0);
		assertEquals("CUSTOM_ADT",adt.getName());
		List<PlSqlTypeAttribute> attributes = adt.getAttributes();
		assertNotNull(attributes);
		assertEquals(3,attributes.size());
		PlSqlTypeAttribute attr1 = attributes.get(0);
		assertNotNull(attr1);
		assertEquals("ID", attr1.getName());
		assertTrue(attr1.getDatatype() instanceof PlSqlDatatypeNumber);
		PlSqlTypeAttribute attr2 = attributes.get(1);
		assertNotNull(attr2);
		assertEquals("NAME",attr2.getName());
		assertTrue(attr2.getDatatype() instanceof PlSqlDatatypeVarchar2);
		PlSqlDatatypeVarchar2 attr2Datatype = (PlSqlDatatypeVarchar2)attr2.getDatatype();
		assertEquals(255, attr2Datatype.getSize());
		PlSqlTypeAttribute attr3 = attributes.get(2);
		assertNotNull(attr3);
		assertEquals("LONG_NAME",attr3.getName());
		assertTrue(attr3.getDatatype() instanceof PlSqlDatatypeVarchar2);
		PlSqlDatatypeVarchar2 attr3Datatype = (PlSqlDatatypeVarchar2)attr3.getDatatype();
		assertEquals(2000, attr3Datatype.getSize());
	}
}
