package com.ness.plsqlparser.tokens;

public class TokenEof extends PlSqlToken {
	public TokenEof() {
		super("");
		type = TType.EOF;
	}

	public TokenEof(String source) {
		super(source);
	}
}
