package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;

public class PlSqlTypeParser extends PlSqlParser {
	public PlSqlTypeParser(PlSqlTokenStream tokens) {
		super(tokens);
	}

	public PlSqlTypeDeclaration parse() throws PlSqlDeclarationParser.MissingDeclarationException {
		valid = false;
		PlSqlTypeDeclaration result = new PlSqlTypeDeclaration();
		return result;
	}
}
