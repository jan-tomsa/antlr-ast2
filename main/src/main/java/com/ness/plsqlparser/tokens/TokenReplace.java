package com.ness.plsqlparser.tokens;

public class TokenReplace extends PlSqlToken {
	public TokenReplace(String source) {
		super(source);
		type = TType.REPLACE;
	}
}
