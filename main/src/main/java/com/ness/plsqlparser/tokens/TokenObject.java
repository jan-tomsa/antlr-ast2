package com.ness.plsqlparser.tokens;

public class TokenObject extends PlSqlToken {
	public TokenObject(String source) {
		super(source);
		type = TType.OBJECT;
	}
}
