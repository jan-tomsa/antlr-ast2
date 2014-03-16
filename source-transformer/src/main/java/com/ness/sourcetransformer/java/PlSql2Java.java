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
	public static String transformIdentifier(String plsqlIdentifier) {
		StringBuilder builder = new StringBuilder();
		char[] uppercasedIdentifier = plsqlIdentifier.toUpperCase().toCharArray();
		boolean lastCharWasAnUnderscore = true;
		for (int i = 0; i < uppercasedIdentifier.length; i++) {
			char c = uppercasedIdentifier[i];
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
