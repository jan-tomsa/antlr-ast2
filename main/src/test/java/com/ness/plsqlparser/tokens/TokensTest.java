package com.ness.plsqlparser.tokens;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokensTest {
    @Test
    public void beginNotFollowedByEnd() {
        PlSqlToken begin = new TokenBegin("BEGIN");
        assertFalse(begin.canBeFollowedBy(TokenEnd.class));
    }

    @Test
    public void beginFollowedByNull() {
        PlSqlToken begin = new TokenBegin("BEGIN");
        assertTrue(begin.canBeFollowedBy(TokenNull.class));
    }
}
