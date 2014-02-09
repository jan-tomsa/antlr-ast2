package com.ness.plsqlparser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlCommand;
import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.parser.PlSqlBlockParser;
import com.ness.plsqlparser.tokens.*;

public class PlSqlBlockParserTest {
    private PlSqlBlockParser parser;
    PlSqlTokenStream tokens;

    @Before
    public void setUp() {
        tokens = new PlSqlTokenStream();
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

	@Ignore
	@Test
	public void nullBlockEnfolded() {
		tokens.add(new TokenBegin("BEGIN"));
		tokens.add(new TokenBegin("BEGIN"));
		tokens.add(new TokenNull("NULL"));
		tokens.add(new TokenSemicolon());
		tokens.add(new TokenEnd("END"));
		tokens.add(new TokenSemicolon());
		tokens.add(new TokenEnd("END"));
		tokens.add(new TokenSemicolon());
		PlSqlBlock block = (PlSqlBlock) parser.parse();
		assertTrue(parser.isValid());
		assertNotNull(block);
		List<PlSqlCommand> commands = block.getCommands();
		assertEquals(1, commands.size());
	}

}
