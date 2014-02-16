package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlTypeDeclarationParser extends PlSqlParser {
	public PlSqlTypeDeclarationParser(PlSqlTokenStream tokens) {
		super(tokens);
	}

	public PlSqlTypeDeclaration parse() throws PlSqlDeclarationParser.MissingDeclarationException {
		valid = true;
		PlSqlTypeDeclaration result = new PlSqlTypeDeclaration();
		// CREATE [OR REPLACE]
		tokens.swallowTokenType(TType.SEPARATOR);
		PlSqlToken token = tokens.currentToken();
		if (token.getType() == TType.OR) {
			tokens.nextToken();
			tokens.swallowTokenType(TType.SEPARATOR);
			token = tokens.currentToken();
			if (token.getType() == TType.REPLACE) {
				result.setReplacing(true);
				tokens.nextToken();
				tokens.swallowTokenType(TType.SEPARATOR);
			}
		}
		// TAKE
		token = tokens.currentToken();
		if (token.getType() != TType.TYPE)
			valid = false;
		else {
			tokens.nextToken();
			tokens.swallowTokenType(TType.SEPARATOR);
		}
		// name
		token = tokens.currentToken();
		if (token.getType() == TType.IDENTIFIER) {
			result.setName(token.getSource());
			token = tokens.nextToken();
		}
		// AS
		tokens.swallowTokenType(TType.SEPARATOR);
		token = tokens.currentToken();
		if (token.getType() != TType.AS)
			valid = false;
		else {
			tokens.nextToken();
			tokens.swallowTokenType(TType.SEPARATOR);
		}

		return result;
	}
}
