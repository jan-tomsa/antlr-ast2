package com.ness.plsql.parser;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognizerSharedState;

public abstract class AbstractPLSQLLexer extends Lexer {
	/**
	 * Default constructor for the lexer, when you do not yet know what
	 * the character stream to be provided is.
	 */
	public AbstractPLSQLLexer() {
	}

	/**
	 * Create a new instance of the lexer using the given character stream as
	 * the input to lex into tokens.
	 *
	 * @param input A valid character stream that contains the ruleSrc code you
	 *              wish to compile (or lex at least)
	 */
	public AbstractPLSQLLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	/**
	 * Internal constructor for ANTLR - do not use.
	 *
	 * @param input The character stream we are going to lex
	 * @param state The shared state object, shared between all lexer comonents
	 */
	public AbstractPLSQLLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}

}
