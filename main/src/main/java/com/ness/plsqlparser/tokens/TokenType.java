package com.ness.plsqlparser.tokens;

public class TokenType extends PlSqlToken {
	public TokenType(String source) {
		super(source);
		type = TType.TYPE;
	}
}
