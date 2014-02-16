package com.ness.plsqlparser.model;

public class PlSqlTypeAttribute extends PlSqlElement {
	private String name;
	private PlSqlDatatype datatype;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDatatype(PlSqlDatatype datatype) {
		this.datatype = datatype;
	}

	public PlSqlDatatype getDatatype() {
		return datatype;
	}
}
