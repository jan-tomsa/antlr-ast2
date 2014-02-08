package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.model.PlSqlElement;
import com.ness.plsqlparser.tokens.PlSqlToken;

import java.util.List;

public class PlSqlParser {
    protected List<PlSqlToken> tokens;
    protected List<PlSqlToken> remainingTokens;
    protected boolean valid;
    protected boolean parsed;

    public boolean isValid() {
        return valid;
    }

    public PlSqlParser(List<PlSqlToken> tokens) {
        this.tokens = tokens;
    }

    public PlSqlElement parse() {
        return null;
    }
    public List<PlSqlToken> getRemainingTokens() {
        return remainingTokens;
    }
}
