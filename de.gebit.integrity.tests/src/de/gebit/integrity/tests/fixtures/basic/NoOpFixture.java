package de.gebit.integrity.tests.fixtures.basic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

/**
 * A simple test fixture which does nothing (except echoing some input).
 * 
 * 
 * @author Rene Schneider
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

	@FixtureMethod(description = "Echo the short '$short$'")
	public Short echoShort(@FixtureParameter(name = "short") Short aShortToEcho) {
		return aShortToEcho;
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

	@FixtureMethod(description = "Echo the calendar '$calendar$'")
	public Calendar echoCalendar(@FixtureParameter(name = "calendar") Calendar aCalendarToEcho) {
		return aCalendarToEcho;
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
}
