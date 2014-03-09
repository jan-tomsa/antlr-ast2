package com.ness.plsqlparser.model.datatype;

import com.ness.plsqlparser.model.PlSqlDatatype;

public class PlSqlDatatypeVarchar2 extends PlSqlDatatype {
	private int size = 255;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
