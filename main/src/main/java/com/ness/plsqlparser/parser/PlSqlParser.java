package com.ness.plsqlparser.parser;

import java.util.List;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlElement;
import com.ness.plsqlparser.tokens.PlSqlToken;

public class PlSqlParser {
    protected PlSqlTokenStream tokens;
    protected List<PlSqlToken> remainingTokens;
    protected boolean valid;
    protected boolean parsed;

    public boolean isValid() {
        return valid;
    }

    public PlSqlParser(PlSqlTokenStream tokens) {
        this.tokens = tokens;
    }

    public PlSqlElement parse() throws PlSqlDeclarationParser.MissingDeclarationException {
        return null;
    }
    public List<PlSqlToken> getRemainingTokens() {
        return remainingTokens;
    }
}
