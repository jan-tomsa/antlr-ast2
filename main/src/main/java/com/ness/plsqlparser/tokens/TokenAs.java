package com.ness.plsqlparser.tokens;

public class TokenAs extends PlSqlToken {
	public TokenAs(String source) {
		super(source);
		type = TType.AS;
	}
}
