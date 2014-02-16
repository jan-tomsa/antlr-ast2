package com.ness.plsqlparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlTokenStream implements Iterable<PlSqlToken> {

	private Iterator<PlSqlToken> iter;
	private List<PlSqlToken> tokens;
	private int currentPos;

	public PlSqlTokenStream() {
		tokens = new ArrayList<>();
		currentPos = 0;
	}

	@Override
	public Iterator<PlSqlToken> iterator() {
		if (iter != null)
			return iter;
		else {
			return new Iterator<PlSqlToken>() {
				@Override
				public boolean hasNext() {
					return (currentPos < tokens.size());
				}

				@Override
				public PlSqlToken next() {
					final PlSqlToken currentToken = tokens.get(currentPos);
					currentPos++;
					return currentToken;
				}

				@Override
				public void remove() {
				}
			};
		}
	}

	public void add(PlSqlToken token) {
		tokens.add(token);
	}

	public PlSqlToken getToken(int index) {
		return tokens.get(index);
	}

	public PlSqlToken nextToken() {
		return iterator().next();
	}

	public void reset() {
		currentPos = 0;
	}

	public int size() {
		return tokens.size();
	}

	public PlSqlToken currentToken() {
		return tokens.get(currentPos);
	}

	public PlSqlToken previousToken() {
		if (currentPos>0)
			return tokens.get(currentPos-1);
		else
			return tokens.get(currentPos);
	}

	public void swallowCurrent() {
		nextToken();
	}

	public boolean isAtLastToken() {
		return currentPos == tokens.size()-1;
	}

	@Override
	public String toString() {
		return "currentPos:" + currentPos + "='" + currentToken().getType().toString() + "'";
	}

	public void swallowTokenType(TType tokenType) {
		while (currentToken().getType() == tokenType) swallowCurrent();
	}

	public void swallowUpToTokenType(TType tokenType) {
		while (currentToken().getType() != tokenType) swallowCurrent();
	}

	public void swallowTokenTypes(TType... tokenTypes){
		while (tokenTypeInArray(currentToken().getType(),tokenTypes))
			swallowCurrent();
	}

	private boolean tokenTypeInArray(TType tokenType, TType[] tokenTypes) {
		final List<TType> typeList = Arrays.asList(tokenTypes);
		return typeList.contains(tokenType);
	}
}
