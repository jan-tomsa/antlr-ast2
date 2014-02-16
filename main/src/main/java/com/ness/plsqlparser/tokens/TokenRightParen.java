package com.ness.plsqlparser.tokens;

public class TokenRightParen extends PlSqlToken {
	public TokenRightParen() {
		this(")");
	}

	public TokenRightParen(String source) {
		super(source);
		type = TType.RIGHT_PAREN;
	}
}
