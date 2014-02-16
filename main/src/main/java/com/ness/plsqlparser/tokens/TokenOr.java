package com.ness.plsqlparser.tokens;

public class TokenOr extends PlSqlToken {
	public TokenOr(String source) {
		super(source);
		type = TType.OR;
	}
}
