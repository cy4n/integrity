/**
 * A default Integrity conversion. 
 */
package de.gebit.integrity.parameter.conversion.conversions.java.strings;

import java.math.BigInteger;

import de.gebit.integrity.parameter.conversion.TargetedConversion;

/**
 * A default Integrity conversion. 
 * 
 * @author Rene Schneider
 * 
 */
public class StringToBigInteger implements TargetedConversion<String, BigInteger> {

	@Override
	public BigInteger convert(String aSource) {
		return new BigInteger(aSource);
	}

	@Override
	public int getPriority() {
		return Integer.MIN_VALUE;
	}

}
