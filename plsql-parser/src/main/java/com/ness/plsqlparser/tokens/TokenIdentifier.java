package com.ness.plsqlparser.tokens;

public class TokenIdentifier extends PlSqlToken {
	public TokenIdentifier(String source) {
		super(source);
		type = TType.IDENTIFIER;
	}
}
