package com.ness.plsqlparser.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlDatatype;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeVarchar2;
import com.ness.plsqlparser.tokens.*;

public class PlSqlTypeAttributeParserTest {
	public static final String SOME_VARIABLE = "SOME_VARIABLE";
	PlSqlTypeAttributeParser parser;

	public void setUpVarchar2(String variableName, String size) throws Exception {
		PlSqlTokenStream tokens = new PlSqlTokenStream();
		tokens.add(new TokenIdentifier(variableName));
		tokens.add(new TokenSeparator(" "));
		tokens.add(new TokenIdentifier("VARCHAR2"));
		tokens.add(new TokenLeftParen());
		tokens.add(new TokenUnsignedInteger(size));
		tokens.add(new TokenRightParen());
		parser = new PlSqlTypeAttributeParser(tokens);
	}

	@Test
	public void testParseVarchar2_10() throws Exception {
		setUpVarchar2(SOME_VARIABLE, "10");
		PlSqlTypeAttribute typeAttribute = parser.parse();
		assertEquals(SOME_VARIABLE,typeAttribute.getName());
		PlSqlDatatype datatype = typeAttribute.getDatatype();
		assertNotNull(datatype);
		assertTrue(datatype instanceof PlSqlDatatypeVarchar2);
		PlSqlDatatypeVarchar2 datatypeV2 = (PlSqlDatatypeVarchar2) datatype;
		assertEquals(10,datatypeV2.getSize());
	}

	@Test
	public void testParseVarchar2_250() throws Exception {
		setUpVarchar2(SOME_VARIABLE, "250");
		PlSqlTypeAttribute typeAttribute = parser.parse();
		PlSqlDatatype datatype = typeAttribute.getDatatype();
		PlSqlDatatypeVarchar2 datatypeV2 = (PlSqlDatatypeVarchar2) datatype;
		assertEquals(250,datatypeV2.getSize());
	}
}
