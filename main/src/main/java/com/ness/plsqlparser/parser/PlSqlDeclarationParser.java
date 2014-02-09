package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.model.PlSqlVariableDeclaration;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlDeclarationParser extends PlSqlParser {
    public PlSqlDeclarationParser(PlSqlTokenStream tokens) {
        super(tokens);
    }

    @Override
    public PlSqlDeclaration parse() throws MissingDeclarationException {
	    if (tokens.currentToken().getType() == TType.BEGIN)
		    throw new MissingDeclarationException();
	    String variableName = null;
	    while (tokens.currentToken().getType() != TType.SEMICOLON) {
		    PlSqlToken token = tokens.nextToken();
		    if (token.getType() == TType.IDENTIFIER) {
			    variableName = token.getSource();
			    tokens.swallowTokenType(TType.SEPARATOR);
			    tokens.swallowUpToTokenType(TType.SEMICOLON); // FIXME: parse datatype
		    }
    }
	    PlSqlToken swallowedSemicolon = tokens.nextToken();
        return new PlSqlVariableDeclaration(variableName);
    }

	class MissingDeclarationException extends Throwable {
	}
}
