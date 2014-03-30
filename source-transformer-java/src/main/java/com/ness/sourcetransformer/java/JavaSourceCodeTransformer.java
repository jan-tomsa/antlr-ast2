package com.ness.sourcetransformer.java;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.ness.plsqlparser.model.PlSqlDatatype;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.model.PlSqlTypeAttribute;
import com.ness.plsqlparser.model.PlSqlTypeDeclaration;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeNumber;
import com.ness.plsqlparser.model.datatype.PlSqlDatatypeVarchar2;
import com.ness.sourcecodeparser.SourceCodeParser;
import com.ness.sourcetransformer.SourceCodeTransformer;

/**
 * Generator of Java class source code from PL/SQL ADT (custom type)
 */
public class JavaSourceCodeTransformer implements SourceCodeTransformer {
	private SourceCodeParser sourceCodeParser;

	@Override
	public String transformCode(String sourceCode) {
		sourceCodeParser = new SourceCodeParser();
		PlSqlElementList elements = sourceCodeParser.parseSource(sourceCode);
		PlSqlTypeDeclaration el1 = (PlSqlTypeDeclaration) elements.getElement(0);
		assertNotNull(elements);
		String className = generateClassName(el1.getName());
		return generateClassJavaDoc(el1) +
				"public class " + className + " {\n" +
				generateAttributes(el1) +
				"\n" +
				generateAccessors(el1) +
				"}\n";
	}

	private String generateAccessors(PlSqlTypeDeclaration adt) {
		StringBuilder result = new StringBuilder();
		List<PlSqlTypeAttribute> attributes = adt.getAttributes();
		for (PlSqlTypeAttribute attr : attributes) {
			result.append(generateAccessorPair(attr));
		}
		return result.toString();
	}

	private String generateAccessorPair(PlSqlTypeAttribute attr) {
		String result = generateGetter(attr) + "\n"
				+ generateSetter(attr) + "\n";
		return result;
	}

	private String generateSetter(PlSqlTypeAttribute attr) {
		final String attrName = PlSql2Java.attrNameToFieldName(attr.getName());
		final String setterName = PlSql2Java.attrNameToSetterName(attr.getName());
		return "\tpublic void " + setterName + "(" + generateDatatype(attr.getDatatype()) + " " + attrName + ") {\n" +
				"\t\tthis." + attrName + " = " + attrName + ";\n" +
				"\t}\n";
	}

	private String generateGetter(PlSqlTypeAttribute attr) {
		final String attrName = PlSql2Java.attrNameToFieldName(attr.getName());
		final String getterName = PlSql2Java.attrNameToGetterName(attr.getName());
		return  "\tpublic " + generateDatatype(attr.getDatatype()) + " " + getterName + "() {\n" +
				"\t\treturn " + attrName + ";\n" +
				"\t}\n";
	}

	private String generateAttributes(PlSqlTypeDeclaration adt) {
		final StringBuilder result = new StringBuilder();
		List<PlSqlTypeAttribute> attributes = adt.getAttributes();
		for (PlSqlTypeAttribute attr : attributes) {
			result.append(generateAttribute(attr));
		}
		return result.toString();
	}

	private String generateAttribute(PlSqlTypeAttribute attr) {
		StringBuilder sb = new StringBuilder("\tprotected ");
		PlSqlDatatype dt = attr.getDatatype();
		sb.append(generateDatatype(dt));
		sb.append(" ");
		sb.append(PlSql2Java.attrNameToFieldName(attr.getName()));
		sb.append(";\n");
		return sb.toString();
	}

	private String generateDatatype(PlSqlDatatype dt) {
		if (dt instanceof PlSqlDatatypeVarchar2) {
			return "String";
		}
		if (dt instanceof PlSqlDatatypeNumber) {
			return "BigDecimal";
		}
		return "Unknown";
	}

	private String generateClassName(String adtName) {
		return PlSql2Java.transformClassName(adtName);
	}

	private String generateClassJavaDoc(PlSqlTypeDeclaration el1) {
		return "/** " + el1.getName() + "\n" +
				"*/\n";
	}
}
