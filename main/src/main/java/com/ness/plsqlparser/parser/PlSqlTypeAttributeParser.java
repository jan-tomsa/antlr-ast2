package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlDatatype;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.tokens.TType;
import com.ness.plsqlparser.tokens.TokenIdentifier;

public class PlSqlTypeAttributeParser extends PlSqlParser {
	public PlSqlTypeAttributeParser(PlSqlTokenStream tokens) {
		super(tokens);
	}

	public PlSqlTypeAttribute parse() {
		final PlSqlTypeAttribute attribute = new PlSqlTypeAttribute();
		//while (tokens.currentToken().getType() != TType.RIGHT_PAREN) {
			if (tokens.currentToken().getType() == TType.IDENTIFIER) {
				attribute.setName(((TokenIdentifier)(tokens.currentToken())).getSource());
				tokens.nextToken();
				tokens.swallowTokenType(TType.SEPARATOR);
			}
			tokens.swallowTokenTypes( TType.COMMA, TType.SEPARATOR );
			PlSqlDatatype datatype = parseDatatype();
			attribute.setDatatype(datatype);
		//}
		return attribute;
	}

	private PlSqlDatatype parseDatatype() {
		while (tokens.currentToken().getType() != TType.COMMA && tokens.currentToken().getType() != TType.RIGHT_PAREN)
			tokens.nextToken();
		return new PlSqlDatatype();
	}
}
