package com.ness.sourcetransformer.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlSql2JavaTest {
	@Test
	public void testTransformIdentifier() {
		String plsqlIdentifier = "CUSTOM_ADT";
		String expectedJavaIdentifier = "CustomAdt";
		String actualJavaIdentifier = PlSql2Java.transformClassName(plsqlIdentifier);
		assertEquals(expectedJavaIdentifier,actualJavaIdentifier);
	}

	@Test
	public void testTransformIdentifier2() {
		String plsqlIdentifier = "CUSTOM_ADT2";
		String expectedJavaIdentifier = "CustomAdt2";
		String actualJavaIdentifier = PlSql2Java.transformClassName(plsqlIdentifier);
		assertEquals(expectedJavaIdentifier,actualJavaIdentifier);
	}

}
