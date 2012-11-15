/**
 * A default Integrity conversion. 
 */
package de.gebit.integrity.parameter.conversion.conversions.java.numbers;

import de.gebit.integrity.parameter.conversion.ConversionFailedException;
import de.gebit.integrity.parameter.conversion.TargetedConversion;
import de.gebit.integrity.parameter.conversion.UnresolvableVariableHandling;

/**
 * A default Integrity conversion. 
 * 
 * @author Rene Schneider
 * 
 */
public class NumberToDouble implements TargetedConversion<Number, Double> {

	@Override
	public Double convert(Number aSource, UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy) throws ConversionFailedException {
		return aSource.doubleValue();
	}

	@Override
	public int getPriority() {
		return Integer.MIN_VALUE;
	}

}
