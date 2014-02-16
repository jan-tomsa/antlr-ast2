package com.ness.plsqlparser.tokens;

public class TokenNot extends PlSqlToken {
	public TokenNot(String source) {
		super(source);
		type = TType.NOT;
	}
}
