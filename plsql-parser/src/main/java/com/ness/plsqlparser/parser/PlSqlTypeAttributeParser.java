package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlDatatype;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeNumber;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeVarchar2;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;
import com.ness.plsqlparser.tokens.TokenIdentifier;
import com.ness.plsqlparser.tokens.TokenUnsignedInteger;

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
		switch (datatypeName.toUpperCase()) {
			case "NUMBER" : tokens.swallowTokenType(TType.SEPARATOR);
							return new PlSqlDatatypeNumber();
			case "VARCHAR2" : return parseVarchar2();
			default : return new PlSqlDatatype();
		}
		//}
	}

	private PlSqlDatatype parseVarchar2() {
		tokens.swallowTokenTypes(TType.LEFT_PAREN, TType.SEPARATOR);
		final PlSqlToken token = tokens.currentToken();
		int size = 0;
		switch (token.getType()) {
			case UNSIGNED_INTEGER :
				size = ((TokenUnsignedInteger)token).intValue();
				tokens.swallowCurrent();
				tokens.swallowTokenType(TType.SEPARATOR);
				tokens.swallowTokenType(TType.RIGHT_PAREN);
		}
		//tokens.swallowTokenTypes(TType.RIGHT_PAREN, TType.SEPARATOR);
		final PlSqlDatatypeVarchar2 datatypeVarchar2 = new PlSqlDatatypeVarchar2();
		datatypeVarchar2.setSize(size);
		return datatypeVarchar2;
	}
}
