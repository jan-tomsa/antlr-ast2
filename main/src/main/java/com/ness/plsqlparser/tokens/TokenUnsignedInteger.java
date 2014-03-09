package com.ness.plsqlparser.tokens;

public class TokenUnsignedInteger extends PlSqlToken {
	public TokenUnsignedInteger(String source) {
		super(source);
		type = TType.UNSIGNED_INTEGER;
	}

	public int intValue() {
		return Integer.parseInt(getSource());
	}
}
