package com.ness.plsqlparser;

import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.tokens.PlSqlToken;

import java.util.List;

public class PlSqlDeclarationParser extends PlSqlParser {
    public PlSqlDeclarationParser(List<PlSqlToken> tokens) {
        super(tokens);
    }

    @Override
    public PlSqlDeclaration parse() {
        remainingTokens = tokens.subList(1,tokens.size());
        return new PlSqlDeclaration("test");
    }
}
