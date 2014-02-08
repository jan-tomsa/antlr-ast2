package com.ness.plsqlparser;

import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlElement;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.parser.PlSqlScriptParser;
import com.ness.plsqlparser.tokens.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlSqlScriptParserTest {

    private PlSqlScriptParser parser;

    @Before
    public void setUp() {
        parser = new PlSqlScriptParser();
    }

    @Test(expected = RuntimeException.class)
    public void parserStateWhenNotParsed() {
        parser.isValid();
    }

    @Test(expected = RuntimeException.class)
    public void emptyParserThrows() {
        parser.parse();
    }

    @Test
    public void emptyBlockIsInvalid() {
        parser.addToken(new TokenBegin("BEGIN"));
        parser.addToken(new TokenEnd("END"));
        parser.addToken(new TokenSemicolon());
        parser.parse();
        assertFalse(parser.isValid());
    }

    @Test
    public void nullBlockIsValid() {
        parser.addToken(new TokenBegin("BEGIN"));
        parser.addToken(new TokenNull("NULL"));
        parser.addToken(new TokenSemicolon());
        parser.addToken(new TokenEnd("END"));
        parser.addToken(new TokenSemicolon());
        PlSqlElementList elements = parser.parse();
        assertTrue(parser.isValid());
        assertEquals(1,elements.size());
        PlSqlElement element = elements.getElement(0);
        if (!(element instanceof PlSqlBlock))
            fail("Parser should have returned PlSqlBlock");
        PlSqlBlock block = (PlSqlBlock) element;
        assertEquals(1,block.getCommands().size());
    }
}