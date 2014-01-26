package com.ness.sourcecodeparser;

import org.junit.Before;
import org.junit.Ignore;

@Ignore
public class SourceCodeTokenizerTest {

    private SourceCodeTokenizer tokenizer;

    @Before
    public void setUp() {
        tokenizer = new SourceCodeTokenizer();
    }

/*    @Test
    public void testTokenizeNull() throws Exception {
        List<PlSqlToken> tokens = tokenizer.tokenize("null;");
        assertNotNull(tokens);
        assertEquals(2,tokens.size());
        PlSqlToken firstToken = tokens.get(0);
        PlSqlToken secondToken = tokens.get(1);
        assertEquals(TType.NULL,firstToken.getType());
        assertEquals(TType.SEMICOLON,secondToken.getType());
    }

    @Test
    public void testTokenizeEmptyBlock() throws Exception {
        String source = "begin\n"
                       +"  null;\n"
                       +"end;";
        List<PlSqlToken> tokens = tokenizer.tokenize(source);
        assertNotNull(tokens);
        assertEquals(5,tokens.size());
        PlSqlToken token1 = tokens.get(0);
        PlSqlToken token2 = tokens.get(1);
        PlSqlToken token3 = tokens.get(2);
        PlSqlToken token4 = tokens.get(3);
        PlSqlToken token5 = tokens.get(4);
        assertEquals(TType.BEGIN, token1.getType());
        assertEquals(TType.NULL, token2.getType());
        assertEquals(TType.SEMICOLON, token3.getType());
        assertEquals(TType.END, token4.getType());
        assertEquals(TType.SEMICOLON, token5.getType());
    }
*/
}
