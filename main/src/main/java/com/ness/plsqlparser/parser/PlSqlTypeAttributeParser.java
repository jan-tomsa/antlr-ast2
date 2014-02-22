package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlDatatype;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeNumber;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeVarchar2;
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
		//while (tokens.currentToken().getType() != TType.COMMA && tokens.currentToken().getType() != TType.RIGHT_PAREN) {
		String datatypeName = tokens.currentToken().getSource();
		tokens.nextToken();
		switch (datatypeName) {
			case "NUMBER" : tokens.swallowTokenType(TType.SEPARATOR);
							return new PlSqlDatatypeNumber();
			case "VARCHAR2" : tokens.swallowTokenTypes(TType.LEFT_PAREN, TType.RIGHT_PAREN,TType.UNSIGNED_INTEGER);
							return new PlSqlDatatypeVarchar2();
			default : return new PlSqlDatatype();
		}
		//}
	}
}
