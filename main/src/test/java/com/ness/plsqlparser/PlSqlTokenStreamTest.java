package com.ness.plsqlparser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ness.plsqlparser.tokens.*;

public class PlSqlTokenStreamTest {

	private PlSqlTokenStream stream;

	@Before
	public void setUp() {
		stream = new PlSqlTokenStream();
		stream.add(new TokenBegin("BEGIN"));
	}

	@Test
	public void basicIteration() {
		int count = 0;
		for (PlSqlToken token : stream) {
			count += 1;
		}
		assertEquals(1,count);
		PlSqlToken token1 = stream.getToken(0);
		assertEquals("BEGIN", token1.getSource());
	}

	@Test
	public void popToken() {
		stream.add(new TokenNull("NULL"));
		stream.add(new TokenEnd("END"));
		PlSqlToken token1 = stream.nextToken();
		assertEquals("BEGIN", token1.getSource());
		PlSqlToken token2 = stream.nextToken();
		assertEquals("NULL", token2.getSource());
		PlSqlToken token3 = stream.nextToken();
		assertEquals("END", token3.getSource());
	}

	@Test
	public void swallowTokenTypesTest() {
		stream.add(new TokenSeparator(" "));
		stream.add(new TokenNull("NULL"));
		stream.add(new TokenSemicolon(";"));
		stream.add(new TokenSeparator("\n"));
		stream.add(new TokenSeparator(" "));
		stream.add(new TokenNull("NULL"));
		stream.add(new TokenSemicolon(";"));
		stream.add(new TokenSeparator(" "));
		stream.add(new TokenEnd("END"));
		stream.nextToken();
		stream.nextToken();
		stream.nextToken();
		stream.swallowTokenTypes(TType.SEMICOLON,TType.SEPARATOR);
		assertEquals(TType.NULL,stream.currentToken().getType());
	}
}
