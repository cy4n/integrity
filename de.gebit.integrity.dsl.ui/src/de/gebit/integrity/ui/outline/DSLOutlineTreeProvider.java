/*******************************************************************************
 * Copyright (c) 2013 Rene Schneider, GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/*
 * generated by Xtext
 */
package de.gebit.integrity.ui.outline;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.label.StylerFactory;

import de.gebit.integrity.dsl.ConstantDefinition;
import de.gebit.integrity.dsl.Import;
import de.gebit.integrity.dsl.Model;
import de.gebit.integrity.dsl.NestedObject;
import de.gebit.integrity.dsl.PackageDefinition;
import de.gebit.integrity.dsl.SuiteDefinition;
import de.gebit.integrity.dsl.SuiteStatementWithResult;
import de.gebit.integrity.dsl.ValueOrEnumValueOrOperation;
import de.gebit.integrity.dsl.VariableDefinition;
import de.gebit.integrity.dsl.VariableEntity;
import de.gebit.integrity.exceptions.ThisShouldNeverHappenException;

/**
 * Customization of the default outline structure.
 * 
 * @author Rene Schneider - initial API and implementation
 * @author tilois - actual first working implementation of a usable outline beyond the Xtext-generated default
 */
// SUPPRESS CHECKSTYLE LONG MethodName
public class DSLOutlineTreeProvider extends DefaultOutlineTreeProvider {
	/*
	 * Note that methods starting with an underscore '_' are called by reflection, based on their signature.
	 * 
	 * E.g. an instance of 'Import' (which is a subclass of 'EObject') would normally go through the method:
	 * _text(EObject) but if there is an method: _text(Import) this would be called instead.
	 * 
	 * See class PolymorphicDispatcher for details.
	 */

	/**
	 * Differentiates between the different occurences of suites. They may be either occur as plain suite definitions or
	 * as a declaration of an dependency or an conclusion.
	 * 
	 * @author tilois
	 */
	protected enum SuiteType {
		/** Normal suite declaration. */
		NORMAL,
		/** Declaration of a suite as a dependency. */
		DEPENDENCY,
		/** Declaration of a suite as a conclusion. */
		CONCLUSION;
	}

	/** Factory to create styles. */
	@Inject
	private StylerFactory styleFactory;

	/** Formatting configuration for the outline. */
	@Inject
	private OutlineFormatting format;

	/** Helps loading images from the PlugIn Path. */
	@Inject
	private IImageHelper imageLoader;

	/** Dynamic Dispatch of {@link #createChildren(IOutlineNode, EObject)}. */
	public void _createChildren(IOutlineNode aParent, SuiteDefinition aSuiteDefinition) {
		for (SuiteDefinition tempSuiteInitializer : aSuiteDefinition.getDependencies()) {
			createSuiteNode(aParent, tempSuiteInitializer, SuiteType.DEPENDENCY);
		}
		for (SuiteDefinition tempSuiteFinalizer : aSuiteDefinition.getFinalizers()) {
			createSuiteNode(aParent, tempSuiteFinalizer, SuiteType.CONCLUSION);
		}
		super._createChildren(aParent, aSuiteDefinition);
	}

	/** Dynamic Dispatch of {@link #createNode(IOutlineNode, EObject)}. */
	protected void _createNode(IOutlineNode aParentNode, SuiteStatementWithResult aModelElement) {
		// Don't let every command clutter the outline structure
	}

	/** Dynamic Dispatch of {@link #createNode(IOutlineNode, EObject)}. */
	protected void _createNode(IOutlineNode aParentNode, SuiteDefinition aSuiteDefinition) {
		createSuiteNode(aParentNode, aSuiteDefinition, SuiteType.NORMAL);
	}

	/** Dynamic Dispatch of {@link #createChildren(IOutlineNode, EObject)}. */
	protected void _createChildren(DocumentRootNode aParentNode, Model aModelElement) {
		for (EObject tempChild : aModelElement.eContents()) {
			createNodeDispatcher.invoke(aParentNode, tempChild);
		}
	}

	/**
	 * Creates a Suite Node, which is a special case because there are multiple types of suite nodes and we need to know
	 * this type before, as the node itself is unaware of it's type.
	 * 
	 * @param aParentNode
	 *            Parent of this node
	 * @param aSuiteDefinition
	 *            Suitedefinition this node is linked to
	 * @param aType
	 *            Type of the suite
	 * @return An {@link EObjectNode} representing this suite declaration, but is unaware of it's suite type.
	 */
	protected EObjectNode createSuiteNode(IOutlineNode aParentNode, SuiteDefinition aSuiteDefinition, SuiteType aType) {
		return createEObjectNode(aParentNode, aSuiteDefinition, suiteImage(aType), text(aSuiteDefinition, aType),
				aType != SuiteType.NORMAL);
	}

	/** Dynamic Dispatch of {@link #_text(Object)}. */
	protected Object _text(Import anImport) {
		final String importNamespace = anImport.getImportedNamespace() != null ? anImport.getImportedNamespace() : "";
		return styleFactory.createFromXtextStyle(importNamespace, format.importTextStyle());
	}

	/** Dynamic Dispatch of {@link #_text(Object)}. */
	protected Object _text(VariableDefinition aVariableDefinition) {
		final StyledString result = new StyledString();
		final String variableName = aVariableDefinition.getName().getName();
		if (variableName != null) {
			result.append(styleFactory.createFromXtextStyle(variableName, format.variableDefinitionTextStyle()));
		}
		String tempValue = getValueOf(aVariableDefinition.getInitialValue());
		if (tempValue != null) {
			result.append(" := ");
			result.append(styleFactory.createFromXtextStyle(tempValue, format.constantDefinitionTextStyle()));
		}
		return appendExplanationTo(result, "Variable");
	}

	/** Dynamic Dispatch of {@link #_text(Object)}. */
	protected Object _text(ConstantDefinition aConstantDefinition) {
		final StyledString result = new StyledString();
		final String constantName = aConstantDefinition.getName().getName();
		if (constantName != null) {
			result.append(styleFactory.createFromXtextStyle(constantName, format.constantDefinitionTextStyle()));
		}
		String tempValue = getValueOf(aConstantDefinition.getValue());
		if (tempValue != null) {
			result.append(" := ");
			result.append(styleFactory.createFromXtextStyle(tempValue, format.constantDefinitionTextStyle()));
		}
		return appendExplanationTo(result, "Constant");
	}

	/**
	 * Gets the textual representation of an arbitrary value from the source. Unless it's an {@link NestedObject}, where
	 * a placeholder is returned, as NestedObjects are expected to hold a multiline value, which can't be displayed in
	 * the outline properly.
	 * 
	 * @param aValue
	 *            Value to display
	 * @return Textual representation of this value.
	 */
	protected String getValueOf(ValueOrEnumValueOrOperation aValue) {
		if (aValue instanceof NestedObject) {
			return "{...}";
		}
		ICompositeNode tempParserNode = NodeModelUtils.getNode(aValue);
		return tempParserNode != null ? tempParserNode.getText() : null;
	}

	/** Dynamic Dispatch of {@link #_text(Object)}. */
	protected Object _text(VariableEntity aParameter) {
		if (!(aParameter.eContainer() instanceof SuiteDefinition)) {
			return null; // Only handling suite parameters
		}
		final String parameterRawText = aParameter.getName() != null ? aParameter.getName() : "";
		final StyledString parameterText = styleFactory.createFromXtextStyle(parameterRawText,
				format.suiteParameterTextStyle());
		return appendExplanationTo(parameterText, "Parameter");
	}

	/** Dynamic Dispatch of {@link #_text(Object)}. */
	protected StyledString _text(SuiteDefinition aSuiteDefinition) {
		final String suiteName = aSuiteDefinition.getName() != null ? aSuiteDefinition.getName() : "";
		return styleFactory.createFromXtextStyle(suiteName, format.suiteTextStyle());
	}

	/**
	 * Returns a styled textual representation of the given suite definition.
	 * 
	 * @param aSuiteDefinition
	 *            Suite definition to get a textual representation.
	 * @param aType
	 *            Type of the suite definition
	 * @return Textual representation of this suite definition.
	 */
	protected StyledString text(SuiteDefinition aSuiteDefinition, SuiteType aType) {
		StyledString tempSuiteText = _text(aSuiteDefinition);
		switch (aType) {
		case NORMAL:
			return appendExplanationTo(tempSuiteText, "Suite");
		case DEPENDENCY:
			return appendExplanationTo(tempSuiteText, "Dependency");
		case CONCLUSION:
			return appendExplanationTo(tempSuiteText, "Conclusion");
		default:
			throw new ThisShouldNeverHappenException("Unknown suite type " + aType + " encountered!");
		}
	}

	/** Dynamic Dispatch of {@link #_image(Object)}. */
	protected Image _image(Import anImport) {
		return image("import");
	}

	/** Dynamic Dispatch of {@link #_image(Object)}. */
	protected Image _image(SuiteDefinition aSuite) {
		return image("suite");
	}

	/** Dynamic Dispatch of {@link #_image(Object)}. */
	protected Image _image(VariableDefinition aVariable) {
		return image("variable");
	}

	/** Dynamic Dispatch of {@link #_image(Object)}. */
	protected Image _image(ConstantDefinition aSuite) {
		return image("constant");
	}

	/** Dynamic Dispatch of {@link #_image(Object)}. */
	protected Image _image(PackageDefinition anPackage) {
		return image("package");
	}

	/** Dynamic Dispatch of {@link #_image(Object)}. */
	protected Object _image(VariableEntity aParameter) {
		if (!(aParameter.eContainer() instanceof SuiteDefinition)) {
			return null; // Only handling suite parameters
		}
		return image("parameter");
	}

	/**
	 * Gets an image for the given suite definition.
	 * 
	 * @param aType
	 *            Type of the suite definition
	 * @return Image represention this suite declaration.
	 */
	protected Image suiteImage(SuiteType aType) {
		switch (aType) {
		case NORMAL:
			return image("suite");
		case DEPENDENCY:
			return image("dependency");
		case CONCLUSION:
			return image("conclusion");
		default:
			throw new ThisShouldNeverHappenException("Unknown suite type " + aType + " encountered!");
		}
	}

	/**
	 * Assumes that all images are loaded from the subfolder "outline" and prepends it to the given name if not already
	 * present. Also assumes that all images are in PNG format and will add that suffix, if not already present.
	 * 
	 * @param aName
	 *            Name of the image.
	 * @return Image.
	 */
	protected Image image(String aName) {
		if (aName == null) {
			return null;
		}
		String tempImageName = aName.startsWith("outline/") ? aName : "outline/" + aName;
		tempImageName = tempImageName.endsWith(".png") ? tempImageName : tempImageName + ".png";
		return imageLoader.getImage(tempImageName);
	}

	/** Dynamic Dispatch of {@link #_isLeaf(Object)}. */
	protected boolean _isLeaf(VariableDefinition aVariableDefintion) {
		return true; // Don't show the VariableEntity in the outline
	}

	/** Dynamic Dispatch of {@link #_isLeaf(Object)}. */
	protected boolean _isLeaf(ConstantDefinition aConstantDefinition) {
		return true; // Don't show the VariableEntity in the outline
	}

	/**
	 * Appends an explanation text to the given styled text.
	 * 
	 * @param aNormalText
	 *            Normal text where the explanation should be appended to.
	 * @param anExplanation
	 *            Explanation text.
	 * @return Styled text with an explanation text.
	 */
	protected StyledString appendExplanationTo(StyledString aNormalText, String anExplanation) {
		StyledString tempExplanation = styleFactory.createFromXtextStyle("(" + anExplanation + ")",
				format.explanationTextStyle());
		return aNormalText.append(" ").append(tempExplanation);
	}
}
