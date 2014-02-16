package com.ness.plsqlparser.tokens;

public class TokenLeftParen extends PlSqlToken {

	public TokenLeftParen() {
		this("(");
	}

	public TokenLeftParen(String source) {
		super(source);
		type = TType.LEFT_PAREN;
	}
}
