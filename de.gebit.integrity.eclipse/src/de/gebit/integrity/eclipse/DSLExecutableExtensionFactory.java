/*
 * generated by Xtext
 */
package de.gebit.integrity.eclipse;

import org.osgi.framework.Bundle;

/**
 * This is an extension of {@link de.gebit.integrity.ui.DSLExecutableExtensionFactory} which pretty much serves the same
 * purpose, just in a different Eclipse plugin.
 * 
 * 
 * @author Rene Schneider
 * 
 */
public class DSLExecutableExtensionFactory extends de.gebit.integrity.ui.DSLExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Activator.getInstance().getBundle();
	}

}
