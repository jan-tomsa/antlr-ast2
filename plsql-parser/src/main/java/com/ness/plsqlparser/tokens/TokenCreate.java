package com.ness.plsqlparser.tokens;

public class TokenCreate extends PlSqlToken {
	public TokenCreate(String source) {
		super(source);
		type = TType.CREATE;
	}
}
