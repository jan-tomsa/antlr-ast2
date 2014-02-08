package com.ness.plsqlparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ness.plsqlparser.tokens.PlSqlToken;

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
}
