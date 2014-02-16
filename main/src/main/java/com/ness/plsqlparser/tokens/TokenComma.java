package com.ness.plsqlparser.tokens;

public class TokenComma extends PlSqlToken {
	public TokenComma() {
		this(",");
	}

	public TokenComma(String source) {
		super(source);
		type = TType.COMMA;
	}
}
