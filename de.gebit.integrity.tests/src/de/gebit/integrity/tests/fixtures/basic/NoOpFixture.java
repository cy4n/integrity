/*******************************************************************************
 * Copyright (c) 2013 Rene Schneider, GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.gebit.integrity.tests.fixtures.basic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import de.gebit.integrity.exceptions.AbortExecutionException;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

/**
 * A simple test fixture which does nothing (except echoing some input).
 * 
 * 
 * @author Rene Schneider - initial API and implementation
 * 
 */
// SUPPRESS CHECKSTYLE LONG Javadoc
public class NoOpFixture {

	@FixtureMethod(description = "Do absolutely nothing.")
	public void noOp() {
		// does nothing!
	}

	@FixtureMethod(description = "Always return true.")
	public boolean returnTrue() {
		return true;
	}

	@FixtureMethod(description = "Echo the string '$string$'")
	public String echoString(@FixtureParameter(name = "string") String aStringToEcho) {
		return aStringToEcho;
	}

	@FixtureMethod(description = "Echo the string array '$strings$'")
	public String[] echoStringArray(@FixtureParameter(name = "strings") String[] someStringsToEcho) {
		return someStringsToEcho;
	}

	@FixtureMethod(description = "Echo the integer '$integer$'")
	public Integer echoInteger(@FixtureParameter(name = "integer") Integer anIntToEcho) {
		return anIntToEcho;
	}

	@FixtureMethod(description = "Echo the number '$number$'")
	public BigDecimal echoNumber(@FixtureParameter(name = "number") Number aNumberToEcho) {
		// This must have been converted to a BigDecimal
		return (BigDecimal) aNumberToEcho;
	}

	@FixtureMethod(description = "Echo the short '$short$'")
	public Short echoShort(@FixtureParameter(name = "short") Short aShortToEcho) {
		return aShortToEcho;
	}

	@FixtureMethod(description = "Echo the float '$float$'")
	public Float echoFloat(@FixtureParameter(name = "float") Float aFloatToEcho) {
		return aFloatToEcho;
	}

	@FixtureMethod(description = "Echo the double '$double$'")
	public Double echoDouble(@FixtureParameter(name = "double") Double aDoubleToEcho) {
		return aDoubleToEcho;
	}

	@FixtureMethod(description = "Echo the byte '$byte$'")
	public Byte echoByte(@FixtureParameter(name = "byte") Byte aByteToEcho) {
		return aByteToEcho;
	}

	@FixtureMethod(description = "Echo the long '$long$'")
	public Long echoLong(@FixtureParameter(name = "long") Long aLongToEcho) {
		return aLongToEcho;
	}

	@FixtureMethod(description = "Echo the BigDecimal '$bigdecimal$'")
	public BigDecimal echoBigDecimal(@FixtureParameter(name = "bigdecimal") BigDecimal aBigDecimalToEcho) {
		return aBigDecimalToEcho;
	}

	@FixtureMethod(description = "Echo the BigInteger '$biginteger$'")
	public BigInteger echoBigInteger(@FixtureParameter(name = "biginteger") BigInteger aBigIntegerToEcho) {
		return aBigIntegerToEcho;
	}

	@FixtureMethod(description = "Echo the date '$date$'")
	public Date echoDate(@FixtureParameter(name = "date") Date aDateToEcho) {
		return aDateToEcho;
	}

	@FixtureMethod(description = "Echo the SQL date '$date$'")
	public java.sql.Date echoSQLDate(@FixtureParameter(name = "date") java.sql.Date aDateToEcho) {
		return aDateToEcho;
	}

	@FixtureMethod(description = "Echo the calendar '$calendar$'")
	public Calendar echoCalendar(@FixtureParameter(name = "calendar") Calendar aCalendarToEcho) {
		return aCalendarToEcho;
	}

	@FixtureMethod(description = "Echo the map array '$map$'")
	public Map<String, Object>[] echoMapArray(@FixtureParameter(name = "map") Map<String, Object>[] aMapToEcho) {
		return aMapToEcho;
	}

	@FixtureMethod(description = "Echo the map '$map$'")
	public Map<String, Object> echoMap(@FixtureParameter(name = "map") Map<String, Object> aMapToEcho) {
		return aMapToEcho;
	}

	@FixtureMethod(descriptionCall = "I was used in a call!", description = "I don't care how I was used!")
	public boolean specificDescriptionCall() {
		return true;
	}

	@FixtureMethod(descriptionTest = "I was used in a test!", description = "I don't care how I was used!")
	public boolean specificDescriptionTest() {
		return true;
	}

	@FixtureMethod(descriptionTest = "I was used in a test!", descriptionCall = "I was used in a call!", description = "I don't care how I was used!")
	public boolean specificDescriptionBoth() {
		return true;
	}

	@FixtureMethod(description = "Echo the {integer?integer $integer$}{string?string '$string$'} and not {^integer?an integer}{^string?a string}!")
	public String echoIntegerOrString(@FixtureParameter(name = "integer") Integer anIntToEcho,
			@FixtureParameter(name = "string") String aStringToEcho) {
		return anIntToEcho != null ? anIntToEcho.toString() : aStringToEcho;
	}

	@FixtureMethod(description = "Echo the {integer?integer $integer$ {^string?and no string}{string?and string '$string$'}!}{^integer?{string?string '$string$' and no integer}.} Cool, huh?")
	public String echoIntegerAndOrString(@FixtureParameter(name = "integer") Integer anIntToEcho,
			@FixtureParameter(name = "string") String aStringToEcho) {
		String tempToEcho = "";
		if (anIntToEcho != null) {
			tempToEcho += anIntToEcho.toString();
		}
		if (aStringToEcho != null) {
			tempToEcho += aStringToEcho;
		}
		return tempToEcho;
	}

	@FixtureMethod(description = "Takes one mandatory and an optional string")
	public boolean takeMandatoryString(@FixtureParameter(name = "optional") String anOptionalParameter,
			@FixtureParameter(name = "mandatory", mandatory = true) String aMandatoryParameter) {
		return true;
	}

	@FixtureMethod(description = "Takes a primitive int which is mandatory by default")
	public boolean takeMandatoryPrimitiveInt(@FixtureParameter(name = "optional") Integer anOptionalParameter,
			@FixtureParameter(name = "mandatory") int aMandatoryParameter) {
		return true;
	}

	@FixtureMethod(description = "Returns an enum: $enum$")
	public Enum echoEnum(@FixtureParameter(name = "enum") Enum aValue) {
		return aValue;
	}

	@FixtureMethod(description = "Returns a named result bean: $result$")
	public EnumNamedResult echoEnumNamedResult(@FixtureParameter(name = "result") EnumNamedResult aValue) {
		return aValue;
	}

	@FixtureMethod(description = "Creates a named result bean with an enum inside")
	public EnumNamedResult createEnumNamedResult() {
		EnumNamedResult tempResult = new EnumNamedResult();
		tempResult.setFirstParameter("foobar");
		tempResult.setSecondParameter(100);
		tempResult.setThirdParameter(Enum.VALUE1);

		return tempResult;
	}

	@FixtureMethod(description = "Throws a runtime exception")
	public boolean throwRuntimeException(@FixtureParameter(name = "message") String aMessage) {
		throw new RuntimeException(aMessage);
	}

	@FixtureMethod(description = "Throws an abortion exception")
	public boolean throwAbortException(@FixtureParameter(name = "message") String aMessage) {
		throw new AbortExecutionException(aMessage);
	}

	public enum Enum {

		VALUE1,

		VALUE2;

	}

	public class EnumNamedResult {

		private String firstParameter;

		private Integer secondParameter;

		private Enum thirdParameter;

		public String getFirstParameter() {
			return firstParameter;
		}

		public void setFirstParameter(String firstParameter) {
			this.firstParameter = firstParameter;
		}

		public Integer getSecondParameter() {
			return secondParameter;
		}

		public void setSecondParameter(Integer secondParameter) {
			this.secondParameter = secondParameter;
		}

		public Enum getThirdParameter() {
			return thirdParameter;
		}

		public void setThirdParameter(Enum thirdParameter) {
			this.thirdParameter = thirdParameter;
		}

	}
}
