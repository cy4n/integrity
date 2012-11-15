/**
 * A default Integrity conversion. 
 */
package de.gebit.integrity.parameter.conversion.conversions.integrity.numbers;

import de.gebit.integrity.dsl.DecimalValue;
import de.gebit.integrity.parameter.conversion.ConversionFailedException;
import de.gebit.integrity.parameter.conversion.TargetedConversion;
import de.gebit.integrity.parameter.conversion.UnresolvableVariableHandling;

/**
 * A default Integrity conversion. 
 * 
 * @author Rene Schneider
 * 
 */
public class DecimalValueToString implements TargetedConversion<DecimalValue, String> {

	@Override
	public String convert(DecimalValue aSource, UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy) throws ConversionFailedException {
		return aSource.getDecimalValue().toString();
	}

	@Override
	public int getPriority() {
		return Integer.MIN_VALUE;
	}

}
