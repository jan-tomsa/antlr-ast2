package com.ness.plsqlparser.tokens;

public class TokenNull extends TokenKeyword {
    public TokenNull(String source) {
        super(source);
        this.type = TType.NULL;
    }
}
