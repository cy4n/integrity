/**
 * A default Integrity conversion. 
 */
package de.gebit.integrity.parameter.conversion.conversions.integrity.strings;

import de.gebit.integrity.dsl.StringValue;
import de.gebit.integrity.parameter.conversion.TargetedConversion;
import de.gebit.integrity.parameter.conversion.ConversionFailedException;
import de.gebit.integrity.parameter.conversion.UnresolvableVariableHandling;

/**
 * A default Integrity conversion. 
 * 
 * @author Rene Schneider
 * 
 */
public class StringValueToLong implements TargetedConversion<StringValue, Long> {

	@Override
	public Long convert(StringValue aSource, UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy) throws ConversionFailedException {
		try {
			return Long.parseLong(aSource.getStringValue());
		} catch (NumberFormatException exc) {
			throw new ConversionFailedException(StringValue.class, Long.class, null, exc);
		}
	}

	@Override
	public int getPriority() {
		return Integer.MIN_VALUE;
	}

}
