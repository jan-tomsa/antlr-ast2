package com.ness.plsqlparser.tokens;

public class TokenSemicolon extends TokenDelimiter {
    public TokenSemicolon(String source) {
        super(source);
        this.type = TType.SEMICOLON;
    }

    public TokenSemicolon() {
        this(";");
    }
}
