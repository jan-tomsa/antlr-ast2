package com.ness.plsqlparser.parser;

import java.util.List;

import com.ness.plsqlparser.model.PlSqlCommand;
import com.ness.plsqlparser.model.PlSqlCommandNull;
import com.ness.plsqlparser.parser.PlSqlParser;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlCommandParser extends PlSqlParser {
    public PlSqlCommandParser(List<PlSqlToken> tokens) {
        super(tokens);
    }

    @Override
    public PlSqlCommand parse() {
        PlSqlToken currentToken = tokens.get(0);
        if (currentToken.getType() == TType.NULL) {
            PlSqlToken followingToken = tokens.get(1);
            if (followingToken.getType() == TType.SEMICOLON) {
                remainingTokens = tokens.subList(2,tokens.size());
                valid = true;
                return new PlSqlCommandNull(currentToken.getSource());
            } else {
                remainingTokens = tokens.subList(1,tokens.size());
                valid = false;
                return null;
            }
        }
        return null;
    }
}
