package com.ness.plsqlparser.tokens;

public class TokenBegin extends TokenKeyword {
    public TokenBegin(String source) {
        super(source);
        this.type = TType.BEGIN;
    }

    @Override
    public boolean canBeFollowedBy(Class<? extends PlSqlToken> followingTokenClass) {
        return followingTokenClass == TokenNull.class;
    }
}
