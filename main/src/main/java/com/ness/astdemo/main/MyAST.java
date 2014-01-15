package com.ness.astdemo.main;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class MyAST extends CommonTree {
	private final OpType operation;

	public MyAST(Token token) {
		super(token);
		operation = (token != null && "+".equals(token.getText()))
				? OpType.PLUS
				: OpType.OTHER;
	}

	public String eval() {
		if (operation == OpType.PLUS)
			return plus();
		else
			return token.getText();
	}

	private String plus() {
		if (getChildCount() == 0)
			return plusLeaf();
		else
			return plusChildren();
	}

	private String plusChildren() {
		int child1 = Integer.parseInt(((MyAST)children.get(0)).eval());
		int child2 = Integer.parseInt(((MyAST)children.get(1)).eval());
		int sum = child1+child2;
		return (new Integer(sum)).toString();
	}

	private String plusLeaf() {
		return token.getText();
	}
}
