package com.ness.plsqlparser.tokens;

public class TokenSolidus extends PlSqlToken {
	public TokenSolidus(String source) {
		super(source);
		type = TType.SOLIDUS;
	}

	public TokenSolidus() {
		this("/");
	}
}
