package com.ness.plsqlparser.tokens;

public enum TType {
    UNDEFINED,
    BEGIN,
    END,
    NULL,
    SEMICOLON,
	DECLARE,
	SEPARATOR,
	IDENTIFIER,
	CREATE,
	OR,
	LEFT_PAREN,
	RIGHT_PAREN,
	AS,
	UNSIGNED_INTEGER,
	NOT,
	SOLIDUS,
	TYPE,
	REPLACE,
	OBJECT,
	EOF,
	COMMA;
}
