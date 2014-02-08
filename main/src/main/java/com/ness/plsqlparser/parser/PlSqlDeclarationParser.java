package com.ness.plsqlparser.parser;

import java.util.List;

import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.tokens.PlSqlToken;

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
