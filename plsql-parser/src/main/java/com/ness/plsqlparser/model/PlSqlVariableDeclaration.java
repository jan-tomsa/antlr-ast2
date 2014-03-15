package com.ness.plsqlparser.model;

public class PlSqlVariableDeclaration extends PlSqlDeclaration {
	private PlSqlDatatype datatype;
	private boolean nullable;
	private PlSqlExpression defaultValueExpression;
	public PlSqlVariableDeclaration(String name) {
		super(name);
	}

	public String getVariableName() {
		return name;
	}

	public PlSqlDatatype getDatatype() {
		return datatype;
	}

	public boolean isNullable() {
		return nullable;
	}

	public PlSqlExpression getDefaultValueExpression() {
		return defaultValueExpression;
	}

	private class PlSqlDatatype {
	}
}
