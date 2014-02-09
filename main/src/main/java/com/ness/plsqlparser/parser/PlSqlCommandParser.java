package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlCommand;
import com.ness.plsqlparser.model.PlSqlCommandNull;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlCommandParser extends PlSqlParser {
    public PlSqlCommandParser(PlSqlTokenStream tokens) {
        super(tokens);
    }

    @Override
    public PlSqlCommand parse() {
        PlSqlToken currentToken = tokens.nextToken();
        if (currentToken.getType() == TType.NULL) {
            PlSqlToken followingToken = tokens.nextToken();
            if (followingToken.getType() == TType.SEMICOLON) {
                valid = true;
                return new PlSqlCommandNull(currentToken.getSource());
            } else {
                valid = false;
                return null;
            }
        }
	    if (currentToken.getType() == TType.BEGIN) {
		    PlSqlCommandParser parser = new PlSqlCommandParser(tokens);
		    return parser.parse();
	    }
        return null;
    }
}
