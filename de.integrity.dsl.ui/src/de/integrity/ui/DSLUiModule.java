/*
 * generated by Xtext
 */
package de.integrity.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;

import de.integrity.ui.documentation.IntegrityEObjectDocumentationProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class DSLUiModule extends de.integrity.ui.AbstractDSLUiModule {
	public DSLUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends IEObjectDocumentationProvider> bindIEObjectDocumentationProvider() {
		return IntegrityEObjectDocumentationProvider.class;
	}
}
