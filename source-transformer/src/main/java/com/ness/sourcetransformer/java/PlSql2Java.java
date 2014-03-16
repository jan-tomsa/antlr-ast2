package com.ness.sourcetransformer.java;

public class PlSql2Java {
	/**
	 * Transforms PL/SQL identifier to a Java one
	 * Rules:
	 * Capitalize first character
	 * Drop underscores
	 * Capitalize first characters after omitted underscores
	 * @param plsqlIdentifier PL/SQL identifier to transform
	 * @return  Java identifier corresponding to plsqlIdentifier
	 */
	public static String transformClassName(String plsqlIdentifier) {
		return transformUnderscoresToCamelcase(plsqlIdentifier, true);
	}

	public static String transformFieldName(String plsqlIdentifier) {
		return transformUnderscoresToCamelcase(plsqlIdentifier, false);
	}

	private static String transformUnderscoresToCamelcase(String plsqlIdentifier, boolean capitalizeFirst) {
		StringBuilder builder = new StringBuilder();
		char[] uppercasedIdentifier = plsqlIdentifier.toUpperCase().toCharArray();
		boolean lastCharWasAnUnderscore = capitalizeFirst;
		for (char c : uppercasedIdentifier) {
			if (c == '_') {
				lastCharWasAnUnderscore = true;
			} else {
				if (lastCharWasAnUnderscore) {
					builder.append(Character.toUpperCase(c));
				} else {
					builder.append(Character.toLowerCase(c));
				}
				lastCharWasAnUnderscore = false;
			}
		}
		return builder.toString();
	}
}
