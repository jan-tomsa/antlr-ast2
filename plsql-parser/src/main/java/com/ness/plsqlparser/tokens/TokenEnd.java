package com.ness.plsqlparser.tokens;

public class TokenEnd extends TokenKeyword {
    public TokenEnd(String source) {
        super(source);
        this.type = TType.END;
    }
}
