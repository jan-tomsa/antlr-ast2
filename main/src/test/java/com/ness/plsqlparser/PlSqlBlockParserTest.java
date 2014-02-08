package com.ness.plsqlparser;

import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlCommand;
import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.parser.PlSqlBlockParser;
import com.ness.plsqlparser.tokens.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlSqlBlockParserTest {
    private PlSqlBlockParser parser;
    List<PlSqlToken> tokens;

    @Before
    public void setUp() {
        tokens = new ArrayList<PlSqlToken>();
        parser = new PlSqlBlockParser(tokens);
    }

    @Test
    public void nullBlock() {
        tokens.add(new TokenBegin("BEGIN"));
        tokens.add(new TokenNull("NULL"));
        tokens.add(new TokenSemicolon());
        tokens.add(new TokenEnd("END"));
        tokens.add(new TokenSemicolon());
        PlSqlBlock block = (PlSqlBlock) parser.parse();
        assertTrue(parser.isValid());
        assertNotNull(block);
        List<PlSqlCommand> commands = block.getCommands();
        assertEquals(1, commands.size());
    }

    @Test
    public void nullBlockWithEmptyDeclare() {
        tokens.add(new TokenDeclare("DECLARE"));
        tokens.add(new TokenBegin("BEGIN"));
        tokens.add(new TokenNull("NULL"));
        tokens.add(new TokenSemicolon());
        tokens.add(new TokenEnd("END"));
        tokens.add(new TokenSemicolon());
        PlSqlBlock block = (PlSqlBlock) parser.parse();
        assertTrue(parser.isValid());
        assertNotNull(block);
        List<PlSqlCommand> commands = block.getCommands();
        assertEquals(1,commands.size());
    }

    @Test
    public void nullBlockWithDeclaration() {
        tokens.add(new TokenDeclare("DECLARE"));
        tokens.add(new TokenSymbol("x"));
        tokens.add(new TokenSymbol("number"));
        tokens.add(new TokenSemicolon());
        tokens.add(new TokenBegin("BEGIN"));
        tokens.add(new TokenNull("NULL"));
        tokens.add(new TokenSemicolon());
        tokens.add(new TokenEnd("END"));
        tokens.add(new TokenSemicolon());
        PlSqlBlock block = (PlSqlBlock) parser.parse();
        assertTrue(parser.isValid());
        assertNotNull(block);
        List<PlSqlDeclaration> declarations = block.getDeclarations();
        assertNotNull(declarations);
        List<PlSqlCommand> commands = block.getCommands();
        assertEquals(1,commands.size());
    }

}
