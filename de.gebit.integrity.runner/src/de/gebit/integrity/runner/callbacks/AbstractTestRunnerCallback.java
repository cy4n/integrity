/**
 * 
 */
package de.gebit.integrity.runner.callbacks;

import com.google.inject.Inject;

import de.gebit.integrity.dsl.NestedObject;
import de.gebit.integrity.dsl.ValueOrEnumValueOrOperation;
import de.gebit.integrity.dsl.ValueOrEnumValueOrOperationCollection;
import de.gebit.integrity.parameter.conversion.UnresolvableVariableHandling;
import de.gebit.integrity.parameter.conversion.ValueConverter;
import de.gebit.integrity.runner.results.FixtureExecutionResult;

/**
 * Abstract base class for test runner callback implementation. Provides some generic functionality required by most
 * callbacks. Using this class is optional; a callback can directly inherit from {@link TestRunnerCallback} as well.
 * 
 * @author Rene Schneider
 * 
 */
public abstract class AbstractTestRunnerCallback extends TestRunnerCallback {

	/**
	 * The value converter to use.
	 */
	@Inject
	protected ValueConverter valueConverter;

	/**
	 * Converts a result value (that is, a value returned by a fixture during a test or call) to a string. This method
	 * uses the fixture instance, if available, to perform the conversion.
	 * 
	 * @param aResultValue
	 *            the result value to convert
	 * @param aResult
	 *            the execution result which provides access to the fixture instance and method
	 * @param aForceIntermediateMapFlag
	 *            whether the conversion should force the usage of an intermediate map (useful for bean types)
	 * @param anUnresolvableVariableHandlingPolicy
	 *            what to do with unresolvable variables
	 * @return the converted string
	 */
	protected String convertResultValueToStringGuarded(Object aResultValue, FixtureExecutionResult aResult,
			boolean aForceIntermediateMapFlag, UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy) {
		if (aResult.getFixtureInstance() != null) {
			return aResult.getFixtureInstance().performValueToStringConversion(aResultValue,
					aResult.getFixtureMethod(), aForceIntermediateMapFlag, anUnresolvableVariableHandlingPolicy);
		} else {
			return valueConverter.convertValueToString(aResultValue, aForceIntermediateMapFlag,
					anUnresolvableVariableHandlingPolicy);
		}
	}

	/**
	 * Determines whether a given {@link ValueOrEnumValueOrOperationCollection} contains at least one nested object.
	 * 
	 * @param aCollection
	 *            the collection to check
	 * @return true or false
	 */
	protected boolean containsNestedObject(ValueOrEnumValueOrOperationCollection aCollection) {
		if (aCollection == null) {
			return false;
		}

		if (aCollection.getValue() instanceof NestedObject) {
			return true;
		} else {
			for (ValueOrEnumValueOrOperation tempSingleValue : aCollection.getMoreValues()) {
				if (tempSingleValue instanceof NestedObject) {
					return true;
				}
			}
		}

		return false;
	}

}
