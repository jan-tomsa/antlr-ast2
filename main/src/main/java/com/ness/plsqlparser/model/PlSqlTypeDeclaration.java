package com.ness.plsqlparser.model;

import java.util.ArrayList;
import java.util.List;

public class PlSqlTypeDeclaration extends PlSqlElement {
	String name;
	private boolean replacing;
	private List<PlSqlTypeAttribute> attributes;

	public PlSqlTypeDeclaration() {
		attributes = new ArrayList<>();
	}

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

	public List<PlSqlTypeAttribute> getAttributes() {
		return attributes;
	}

	public void addAttribute(PlSqlTypeAttribute attribute) {
		attributes.add(attribute);
	}
}
