package com.ness.plsqlparser.tokens;

import java.util.ArrayList;
import java.util.List;

public class PlSqlToken {
    private String source;

    TType type = TType.UNDEFINED;
	private List<PlSqlToken> children;

    public PlSqlToken(String source) {
        this.source = source;
	    this.children = new ArrayList<>();
    }

    public String getSource() {
        return source;
    }

    public TType getType() {
        return type;
    }

    public boolean canBeFollowedBy(Class<? extends PlSqlToken> followingTokenClass) {
        return false;
    }

	public List<PlSqlToken> getChildren() {
		return children;
	}

	public List<PlSqlToken> addChild(PlSqlToken token) {
		children.add(token);
		return children;
	}
}
