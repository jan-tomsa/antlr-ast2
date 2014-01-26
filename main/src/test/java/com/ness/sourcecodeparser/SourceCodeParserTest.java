package com.ness.sourcecodeparser;

import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ness.plsqlparser.model.*;

public class SourceCodeParserTest {

    private SourceCodeParser sourceCodeParser;

    @Before
    public void setUp() {
        sourceCodeParser = new SourceCodeParser();
    }

    @Test
    public void testEmpyBlock() {
        PlSqlElementList elements = sourceCodeParser.parseSource("begin null; end;");
        assertNotNull(elements);
        assertEquals(1, elements.size());
        PlSqlElement elem = elements.getElement(0);
        assertTrue(elem instanceof PlSqlBlock);
        PlSqlBlock block = (PlSqlBlock) elem;
        List<PlSqlDeclaration> declarations = block.getDeclarations();
        assertNotNull(declarations);
        List<PlSqlCommand> commands = block.getCommands();
        assertNotNull(commands);
        assertEquals(1,commands.size());
        PlSqlCommand command = commands.get(0);
        assertTrue(command instanceof PlSqlCommandNull);
    }

    @Ignore @Test
    public void testEmptyBlockWithDeclaration() {
        PlSqlElementList elements = sourceCodeParser.parseSource("declare x number; begin null; end;");
        assertNotNull(elements);
        assertEquals(1, elements.size());
        PlSqlElement elem = elements.getElement(0);
        assertTrue(elem instanceof PlSqlBlock);
        PlSqlBlock block = (PlSqlBlock) elem;
        List<PlSqlDeclaration> declarations = block.getDeclarations();
        assertNotNull(declarations);
        assertEquals(1,declarations.size());
        List<PlSqlCommand> commands = block.getCommands();
        assertNotNull(commands);
        assertEquals(1,commands.size());
        PlSqlCommand command = commands.get(0);
        assertTrue(command instanceof PlSqlCommandNull);
    }
}
