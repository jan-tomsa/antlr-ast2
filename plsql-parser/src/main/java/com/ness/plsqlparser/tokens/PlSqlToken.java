package com.ness.plsqlparser.tokens;

public class PlSqlToken {
    private String source;

    TType type = TType.UNDEFINED;

    public PlSqlToken(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public TType getType() {
        return type;
    }

    public boolean canBeFollowedBy(Class<? extends PlSqlToken> followingTokenClass) {
        return false;
    }
}
