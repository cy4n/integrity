/**
 * 
 */
package de.gebit.integrity.parameter.conversion;

import de.gebit.integrity.dsl.ValueOrEnumValueOrOperation;
import de.gebit.integrity.dsl.ValueOrEnumValueOrOperationCollection;
import de.gebit.integrity.operations.OperationWrapper.UnexecutableException;
import de.gebit.integrity.utils.ParameterUtil.UnresolvableVariableException;

/**
 * The value converter is responsible for conversion of values during test execution and/or inside the Eclipse
 * integration.
 * 
 * @author Rene Schneider
 * 
 */
public interface ValueConverter {

	/**
	 * Convert a given single Integrity or Java type value to a given target type (which is always a Java type).
	 * 
	 * @param aTargetClass
	 *            the target type
	 * @param aValue
	 *            the value
	 * @param anUnresolvableVariableHandlingPolicy
	 *            Defines the policy how unresolvable variable references (no variable given or no
	 *            {@link de.gebit.integrity.parameter.variables.VariableManager} available) shall be treated
	 * @return the converted object
	 */
	Object convertValueToParamType(Class<?> aTargetClass, Object aValue,
			UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy);

	/**
	 * Converts a given {@link ValueOrEnumValueOrOperation} to a given Java type class, if possible.
	 * 
	 * @param aTargetClass
	 *            the target type
	 * @param aValue
	 *            the value
	 * @param anUnresolvableVariableHandlingPolicy
	 *            Defines the policy how unresolvable variable references (no variable given or no
	 *            {@link de.gebit.integrity.parameter.variables.VariableManager} available) shall be treated
	 * @return the converted value
	 * @throws UnresolvableVariableException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws UnexecutableException
	 */
	Object convertEncapsulatedValueToParamType(Class<?> aTargetClass, ValueOrEnumValueOrOperation aValue,
			UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy) throws UnresolvableVariableException,
			ClassNotFoundException, UnexecutableException, InstantiationException;

	/**
	 * Converts a given value collection to a given Java type class, if possible. Will return an array if the collection
	 * contains more than one item.
	 * 
	 * @param aTargetClass
	 *            the target type
	 * @param aCollection
	 *            the value collection
	 * @param anUnresolvableVariableHandlingPolicy
	 *            Defines the policy how unresolvable variable references (no variable given or no
	 *            {@link de.gebit.integrity.parameter.variables.VariableManager} available) shall be treated
	 * @return the converted value
	 * @throws UnresolvableVariableException
	 * @throws ClassNotFoundException
	 * @throws UnexecutableException
	 * @throws InstantiationException
	 */
	Object convertEncapsulatedValueCollectionToParamType(Class<?> aTargetClass,
			ValueOrEnumValueOrOperationCollection aCollection,
			UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy) throws UnresolvableVariableException,
			ClassNotFoundException, UnexecutableException, InstantiationException;

	/**
	 * Converts a given value to a String. This method is intended to be used for the output of values (for example in
	 * test results, on the console etc). In comparison to
	 * {@link #convertValueToStringArray(Object, UnresolvableVariableHandling)}, this method always returns only a
	 * single String value, concatenating arrays first if necessary.
	 * 
	 * @param aValue
	 *            the value (can be an {@link ValueOrEnumValueOrOperationCollection} or a plain Java Object)
	 * @param anUnresolvableVariableHandlingPolicy
	 *            Defines the policy how unresolvable variable references (no variable given or no
	 *            {@link de.gebit.integrity.parameter.variables.VariableManager} available) shall be treated
	 * @return the string
	 */
	String convertValueToString(Object aValue, UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy);

	/**
	 * Converts a given value to a String array. This method is intended to be used for the output of values (for
	 * example in test results, on the console etc).
	 * 
	 * @param aValue
	 *            the value (can be an {@link ValueOrEnumValueOrOperationCollection} or a plain Java Object)
	 * @param anUnresolvableVariableHandlingPolicy
	 *            Defines the policy how unresolvable variable references (no variable given or no
	 *            {@link de.gebit.integrity.parameter.variables.VariableManager} available) shall be treated
	 * @return the string array
	 */
	String[] convertValueToStringArray(Object aValue, UnresolvableVariableHandling anUnresolvableVariableHandlingPolicy);
}
