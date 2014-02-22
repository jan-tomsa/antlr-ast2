package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
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
		// TYPE
		validateToken(TType.TYPE);
		// name
		token = tokens.currentToken();
		if (token.getType() == TType.IDENTIFIER) {
			result.setName(token.getSource());
			token = tokens.nextToken();
		}
		// AS
		validateToken(TType.AS);
		// OBJECT
		validateToken(TType.OBJECT);
		// (
		validateToken(TType.LEFT_PAREN);
		//
		parseAttributes(result);
		return result;
	}

	private void parseAttributes(PlSqlTypeDeclaration result) {
		PlSqlTypeAttributeParser parser = new PlSqlTypeAttributeParser(tokens);
		while (tokens.currentToken().getType() != TType.RIGHT_PAREN) {
			final PlSqlTypeAttribute attribute = parser.parse();
			result.addAttribute(attribute);
			tokens.swallowTokenTypes(TType.COMMA, TType.SEPARATOR);
		}
	}

	private void validateToken(TType type) {
		PlSqlToken token;
		tokens.swallowTokenType(TType.SEPARATOR);
		token = tokens.currentToken();
		if (token.getType() != type)
			valid = false;
		else {
			tokens.nextToken();
			tokens.swallowTokenType(TType.SEPARATOR);
		}
	}
}
