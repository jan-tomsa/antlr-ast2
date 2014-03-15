package com.ness.plsqlparser.tokens;

public class TokenSeparator extends PlSqlToken {
	public TokenSeparator(String source) {
		super(source);
		type = TType.SEPARATOR;
	}
}
