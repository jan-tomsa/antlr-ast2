package com.ness.plsqlparser.model;

public class PlSqlTypeDeclaration extends PlSqlElement {
	String name;
	private boolean replacing;

	public String getName() {
		return name;
	}

	public boolean isReplacing() {
		return replacing;
	}

	public void setReplacing(boolean replacing) {
		this.replacing = replacing;
	}

	public void setName(String name) {
		this.name = name;
	}
}
