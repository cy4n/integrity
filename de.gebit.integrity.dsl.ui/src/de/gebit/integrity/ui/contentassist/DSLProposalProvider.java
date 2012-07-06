/*
 * generated by Xtext
 */
package de.gebit.integrity.ui.contentassist;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.util.jdt.IJavaElementFinder;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.inject.Inject;

import de.gebit.integrity.dsl.ArbitraryParameterOrResultName;
import de.gebit.integrity.dsl.Call;
import de.gebit.integrity.dsl.CallDefinition;
import de.gebit.integrity.dsl.MethodReference;
import de.gebit.integrity.dsl.NamedResult;
import de.gebit.integrity.dsl.Parameter;
import de.gebit.integrity.dsl.ParameterName;
import de.gebit.integrity.dsl.ParameterTableHeader;
import de.gebit.integrity.dsl.ParameterTableValue;
import de.gebit.integrity.dsl.ResultName;
import de.gebit.integrity.dsl.ResultTableHeader;
import de.gebit.integrity.dsl.Suite;
import de.gebit.integrity.dsl.SuiteDefinition;
import de.gebit.integrity.dsl.SuiteParameter;
import de.gebit.integrity.dsl.TableTest;
import de.gebit.integrity.dsl.TableTestRow;
import de.gebit.integrity.dsl.Test;
import de.gebit.integrity.dsl.TestDefinition;
import de.gebit.integrity.dsl.ValueOrEnumValueCollection;
import de.gebit.integrity.dsl.Variable;
import de.gebit.integrity.fixtures.ArbitraryParameterEnumerator;
import de.gebit.integrity.fixtures.ArbitraryParameterEnumerator.ArbitraryParameterDefinition;
import de.gebit.integrity.fixtures.CustomProposalFixture;
import de.gebit.integrity.fixtures.CustomProposalProvider;
import de.gebit.integrity.fixtures.CustomProposalProvider.CustomProposalDefinition;
import de.gebit.integrity.ui.utils.FixtureTypeWrapper;
import de.gebit.integrity.ui.utils.JavadocUtil;
import de.gebit.integrity.utils.IntegrityDSLUtil;
import de.gebit.integrity.utils.ParamAnnotationTuple;
import de.gebit.integrity.utils.ResultFieldTuple;

/**
 * This class is the extension point to implement custom proposal provider, aka "content assist".
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 * 
 * @author Rene Schneider
 */
@SuppressWarnings("restriction")
public class DSLProposalProvider extends AbstractDSLProposalProvider {

	/**
	 * The element finder.
	 */
	@Inject
	IJavaElementFinder elementFinder;

	/**
	 * This is added to the proposal priorities from fixture proposal providers to ensure they're listed top in the list
	 * when they return 0 as priority.
	 */
	private static final int DEFAULT_PROPOSAL_BASE = 10000;

	/**
	 * Creates a proposal (basically resembles the overridden method, but creates an
	 * {@link IntegrityConfigurableCompletionProposal} instead, which stores the {@link ContentAssistContext}.
	 */
	@Override
	protected ConfigurableCompletionProposal doCreateProposal(String aProposal, StyledString aDisplayString,
			Image anImage, int aPriority, ContentAssistContext aContext) {
		int tempReplacementOffset = aContext.getReplaceRegion().getOffset();
		int tempReplacementLength = aContext.getReplaceRegion().getLength();
		ConfigurableCompletionProposal tempResult = new IntegrityConfigurableCompletionProposal(aProposal,
				tempReplacementOffset, tempReplacementLength, aProposal.length(), anImage, aDisplayString, null, null,
				aContext);
		tempResult.setPriority(aPriority);
		tempResult.setMatcher(aContext.getMatcher());
		tempResult.setReplaceContextLength(aContext.getReplaceContextLength());
		return tempResult;
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeTest_Parameters(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeTest_Parameters(aModel, anAssignment, aContext, anAcceptor);

		Test tempTest = (Test) aModel;
		TestDefinition tempTestDef = tempTest.getDefinition();
		if (tempTestDef != null) {
			Set<String> tempAlreadyUsedParameters = new HashSet<String>();
			for (Parameter tempParameter : tempTest.getParameters()) {
				tempAlreadyUsedParameters.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempParameter
						.getName()));
			}
			completeParametersInternal(tempAlreadyUsedParameters, tempTestDef.getFixtureMethod(), null, aContext,
					anAcceptor);
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeCall_Parameters(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeCall_Parameters(aModel, anAssignment, aContext, anAcceptor);

		Call tempCall = (Call) aModel;
		CallDefinition tempCallDef = tempCall.getDefinition();
		if (tempCallDef != null) {
			Set<String> tempAlreadyUsedParameters = new HashSet<String>();
			for (Parameter tempParameter : tempCall.getParameters()) {
				tempAlreadyUsedParameters.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempParameter
						.getName()));
			}
			completeParametersInternal(tempAlreadyUsedParameters, tempCallDef.getFixtureMethod(), null, aContext,
					anAcceptor);
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeTableTest_Parameters(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeTableTest_Parameters(aModel, anAssignment, aContext, anAcceptor);

		TableTest tempTableTest = (TableTest) aModel;
		TestDefinition tempTestDef = tempTableTest.getDefinition();
		if (tempTestDef != null) {
			Set<String> tempAlreadyUsedParameters = new HashSet<String>();
			for (Parameter tempParameter : tempTableTest.getParameters()) {
				tempAlreadyUsedParameters.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempParameter
						.getName()));
			}
			for (ParameterTableHeader tempHeader : tempTableTest.getParameterHeaders()) {
				tempAlreadyUsedParameters
						.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempHeader.getName()));
			}

			completeParametersInternal(tempAlreadyUsedParameters, tempTestDef.getFixtureMethod(), null, aContext,
					anAcceptor);
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeTableTest_ParameterHeaders(EObject aModel, Assignment anAssignment,
			ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {
		super.completeTableTest_ParameterHeaders(aModel, anAssignment, aContext, anAcceptor);

		TableTest tempTableTest = null;
		if (aModel instanceof TableTest) {
			tempTableTest = (TableTest) aModel;
		} else {
			tempTableTest = (TableTest) NodeModelUtils.findActualSemanticObjectFor(aContext.getCurrentNode());
		}

		TestDefinition tempTestDef = tempTableTest.getDefinition();
		if (tempTestDef != null) {
			Set<String> tempAlreadyUsedParameters = new HashSet<String>();
			for (Parameter tempParameter : tempTableTest.getParameters()) {
				tempAlreadyUsedParameters.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempParameter
						.getName()));
			}
			for (ParameterTableHeader tempParameterHeader : tempTableTest.getParameterHeaders()) {
				tempAlreadyUsedParameters.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempParameterHeader
						.getName()));
			}
			completeParametersInternal(tempAlreadyUsedParameters, tempTestDef.getFixtureMethod(), null, aContext,
					anAcceptor);

			Set<String> tempAlreadyUsedResults = new HashSet<String>();
			for (ResultTableHeader tempResultHeader : tempTableTest.getResultHeaders()) {
				tempAlreadyUsedResults.add(IntegrityDSLUtil
						.getExpectedResultNameStringFromTestResultName(tempResultHeader.getName()));
			}
			completeNamedResultsInternal(tempAlreadyUsedResults, tempTestDef.getFixtureMethod(), null, aContext,
					anAcceptor);
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeParameterTableHeader_Name(EObject aModel, Assignment anAssignment,
			ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {
		super.completeParameterTableHeader_Name(aModel, anAssignment, aContext, anAcceptor);

		TableTest tempTableTest = (TableTest) aModel;
		TestDefinition tempTestDef = (tempTableTest).getDefinition();
		if (tempTestDef != null) {
			Set<String> tempAlreadyUsedParameters = new HashSet<String>();
			for (ParameterTableHeader tempParameterHeader : tempTableTest.getParameterHeaders()) {
				tempAlreadyUsedParameters.add(IntegrityDSLUtil.getParamNameStringFromParameterName(tempParameterHeader
						.getName()));
			}
			completeParametersInternal(tempAlreadyUsedParameters, tempTestDef.getFixtureMethod(), null, aContext,
					anAcceptor);
		}
	}

	private void completeParametersInternal(Set<String> someAlreadyUsedParameters, MethodReference aMethod,
			String aPrefix, ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {

		if (aMethod == null || aMethod.getMethod() == null) {
			return;
		}

		Map<String, String> tempJavadocMap = JavadocUtil.getMethodParamJavadoc(aMethod.getMethod(), elementFinder);

		List<ParamAnnotationTuple> tempParamList = IntegrityDSLUtil.getAllParamNamesFromFixtureMethod(aMethod);
		for (ParamAnnotationTuple tempParam : tempParamList) {
			if (!someAlreadyUsedParameters.contains(tempParam.getParamName())) {
				String tempJavadocDescription = tempJavadocMap != null ? tempJavadocMap.get(tempParam
						.getJavaParamName()) : null;
				String tempDisplayText = null;
				if (tempJavadocDescription != null) {
					tempDisplayText = tempParam.getParamName() + ": " + tempJavadocDescription;
				} else {
					tempDisplayText = tempParam.getParamName();
				}
				anAcceptor.accept(createCompletionProposal((aPrefix != null ? aPrefix : "") + tempParam.getParamName(),
						tempDisplayText, null, aContext));
			}
		}
	}

	private void completeNamedResultsInternal(Set<String> someAlreadyUsedResults, MethodReference aMethod,
			String aPrefix, ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {

		if (aMethod == null || aMethod.getMethod() == null) {
			return;
		}

		List<ResultFieldTuple> tempResultFields = IntegrityDSLUtil.getAllResultNamesFromFixtureMethod(aMethod);
		for (ResultFieldTuple tempResult : tempResultFields) {
			if (!someAlreadyUsedResults.contains(tempResult.getResultName())) {
				String tempJavadocDescription = JavadocUtil.getJvmFieldJavadoc(tempResult.getField(), elementFinder);
				String tempDisplayText = tempResult.getResultName();

				ICompletionProposal tempCompletionProposal = createCompletionProposal((aPrefix != null ? aPrefix : "")
						+ tempResult.getResultName() + " = ", tempDisplayText, null, aContext);
				if (tempCompletionProposal instanceof ConfigurableCompletionProposal) {
					if (tempJavadocDescription != null) {
						((ConfigurableCompletionProposal) tempCompletionProposal)
								.setAdditionalProposalInfo(tempJavadocDescription);
					}
				}
				anAcceptor.accept(tempCompletionProposal);
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeArbitraryParameterOrResultName_Identifier(EObject aModel, Assignment anAssignment,
			ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {
		super.completeArbitraryParameterOrResultName_Identifier(aModel, anAssignment, aContext, anAcceptor);

		EObject tempContainer = null;
		if (aModel instanceof Parameter) {
			tempContainer = aModel.eContainer();
		} else if (aModel instanceof ArbitraryParameterOrResultName) {
			tempContainer = aModel.eContainer().eContainer();
		} else if (aModel instanceof NamedResult) {
			tempContainer = aModel.eContainer();
		} else {
			// assume the model is already the outer container we search for
			tempContainer = aModel;
		}

		// We need these parameter and result maps in order to sort out proposals for parameters/results already given
		Map<String, Object> tempParameterMap = null;
		Map<String, Object> tempExpectedResultMap = null;
		MethodReference tempMethodReference = null;
		if (tempContainer instanceof Test) {
			Test tempTest = (Test) tempContainer;
			tempParameterMap = IntegrityDSLUtil.createParameterMap(tempTest, null, true, false);
			tempExpectedResultMap = IntegrityDSLUtil.createExpectedResultMap(tempTest, null, true);
			tempMethodReference = tempTest.getDefinition().getFixtureMethod();
		} else if (tempContainer instanceof TableTest) {
			TableTest tempTest = (TableTest) tempContainer;
			tempParameterMap = IntegrityDSLUtil.createParameterMap(tempTest, null, null, true, false);
			tempExpectedResultMap = new LinkedHashMap<String, Object>();
			for (ResultTableHeader tempHeader : tempTest.getResultHeaders()) {
				tempExpectedResultMap.put(
						IntegrityDSLUtil.getExpectedResultNameStringFromTestResultName(tempHeader.getName()), null);
			}
			tempMethodReference = tempTest.getDefinition().getFixtureMethod();
		} else if (tempContainer instanceof Call) {
			Call tempCall = (Call) tempContainer;
			tempParameterMap = IntegrityDSLUtil.createParameterMap(tempCall.getParameters(), null, true, false);
			tempMethodReference = tempCall.getDefinition().getFixtureMethod();
		}

		if (tempParameterMap != null && tempMethodReference != null && tempMethodReference.getType() != null) {
			IJavaElement tempSourceMethod = (IJavaElement) elementFinder.findElementFor(tempMethodReference.getType());

			CompilationUnit tempCompilationUnit = (CompilationUnit) tempSourceMethod.getParent();
			try {
				FixtureTypeWrapper tempFixtureClassWrapper = new FixtureTypeWrapper(tempCompilationUnit.getTypes()[0]);

				ArbitraryParameterEnumerator tempEnumerator = tempFixtureClassWrapper
						.instantiateArbitraryParameterEnumerator();
				if (tempEnumerator != null) {
					Map<String, Object> tempFixedParameterMap = null;
					if (tempContainer instanceof Test) {
						Test tempTest = (Test) tempContainer;
						tempFixedParameterMap = IntegrityDSLUtil.createParameterMap(tempTest.getParameters(), null,
								false, true);
					} else if (tempContainer instanceof TableTest) {
						TableTest tempTest = (TableTest) tempContainer;
						tempFixedParameterMap = IntegrityDSLUtil.createParameterMap(tempTest.getParameters(), null,
								false, true);
					} else if (tempContainer instanceof Call) {
						Call tempCall = (Call) tempContainer;
						tempFixedParameterMap = IntegrityDSLUtil.createParameterMap(tempCall.getParameters(), null,
								false, true);
					}

					resolveVariables(tempFixedParameterMap);

					tempFixtureClassWrapper.convertParameterValuesToFixtureDefinedTypes(tempMethodReference.getMethod()
							.getSimpleName(), tempFixedParameterMap, false);

					// first fetch the arbitrary parameter names...
					List<ArbitraryParameterDefinition> tempParameterDescriptions = tempEnumerator
							.defineArbitraryParameters(tempMethodReference.getMethod().getSimpleName(),
									tempFixedParameterMap);
					if (tempParameterDescriptions != null) {
						for (ArbitraryParameterDefinition tempParameterDescription : tempParameterDescriptions) {
							String tempName = tempParameterDescription.getName();
							if (!tempParameterMap.containsKey(tempName)) {
								String tempDescription = tempName;
								if (tempParameterDescription.getDescription() != null) {
									tempDescription += ": " + tempParameterDescription.getDescription();
								}
								String tempSuffix = (tempContainer instanceof TableTest) ? "" : ": ";
								anAcceptor.accept(createCompletionProposal(tempName + tempSuffix, tempDescription,
										null, aContext));
							}
						}
					}

					// ...then add the arbitrary result names
					if (tempExpectedResultMap != null) {
						List<ArbitraryParameterDefinition> tempResultDescriptions = tempEnumerator
								.defineArbitraryResults(tempMethodReference.getMethod().getSimpleName(),
										tempFixedParameterMap);
						if (tempResultDescriptions != null) {
							for (ArbitraryParameterDefinition tempResultDescription : tempResultDescriptions) {
								String tempName = tempResultDescription.getName();
								if (!tempExpectedResultMap.containsKey(tempName)) {
									String tempDescription = tempName;
									if (tempResultDescription.getDescription() != null) {
										tempDescription += ": " + tempResultDescription.getDescription();
									}
									anAcceptor.accept(createCompletionProposal(tempName + "=", tempDescription, null,
											aContext));
								}
							}
						}
					}
				}
			} catch (JavaModelException exc) {
				exc.printStackTrace();
			}
		}
	}

	/**
	 * Resolves all variable values in the given map.
	 * 
	 * @param aParameterMap
	 */
	private static void resolveVariables(Map<String, Object> aParameterMap) {
		for (Entry<String, Object> tempEntry : aParameterMap.entrySet()) {
			if (tempEntry.getValue() instanceof Variable) {
				tempEntry.setValue(IntegrityDSLUtil.resolveVariableStatically((Variable) tempEntry.getValue()));
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeTest_Result(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeTest_Result(aModel, anAssignment, aContext, anAcceptor);

		if (aModel instanceof Test) {
			Test tempTest = (Test) aModel;
			MethodReference tempMethod = tempTest.getDefinition().getFixtureMethod();
			List<Parameter> tempAllParameters = tempTest.getParameters();

			if (tempMethod != null && isCustomProposalFixture(tempMethod)) {
				try {
					IJavaElement tempSourceMethod = (IJavaElement) elementFinder.findElementFor(tempMethod.getType());
					CompilationUnit tempCompilationUnit = (CompilationUnit) tempSourceMethod.getParent();
					FixtureTypeWrapper tempFixtureClassWrapper = new FixtureTypeWrapper(
							tempCompilationUnit.getTypes()[0]);

					Map<String, Object> tempParamMap = IntegrityDSLUtil.createParameterMap(tempAllParameters, null,
							true, true);

					Object tempResultValue = tempFixtureClassWrapper.convertResultValueToFixtureDefinedType(tempMethod
							.getMethod().getSimpleName(), null, tempTest.getResult());

					completeResultValuesInternal(null, tempMethod, tempResultValue, tempParamMap, aContext, anAcceptor);
				} catch (JavaModelException exc) {
					exc.printStackTrace();
				}
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeNamedResult_Value(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeNamedResult_Value(aModel, anAssignment, aContext, anAcceptor);

		if (aModel instanceof NamedResult) {
			NamedResult tempResult = (NamedResult) aModel;
			Test tempTest = (Test) tempResult.eContainer();
			MethodReference tempMethod = tempTest.getDefinition().getFixtureMethod();
			List<Parameter> tempAllParameters = tempTest.getParameters();

			if (tempMethod != null && isCustomProposalFixture(tempMethod)) {
				try {
					IJavaElement tempSourceMethod = (IJavaElement) elementFinder.findElementFor(tempMethod.getType());
					CompilationUnit tempCompilationUnit = (CompilationUnit) tempSourceMethod.getParent();
					FixtureTypeWrapper tempFixtureClassWrapper = new FixtureTypeWrapper(
							tempCompilationUnit.getTypes()[0]);

					Map<String, Object> tempParamMap = IntegrityDSLUtil.createParameterMap(tempAllParameters, null,
							true, true);

					Object tempResultValue = tempFixtureClassWrapper.convertResultValueToFixtureDefinedType(tempMethod
							.getMethod().getSimpleName(), tempResult.getName(), tempResult.getValue());

					completeResultValuesInternal(tempResult.getName(), tempMethod, tempResultValue, tempParamMap,
							aContext, anAcceptor);
				} catch (JavaModelException exc) {
					exc.printStackTrace();
				}
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeParameter_Value(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeParameter_Value(aModel, anAssignment, aContext, anAcceptor);

		if (aModel instanceof Parameter) {
			Parameter tempParam = (Parameter) aModel;

			MethodReference tempMethod = null;
			List<Parameter> tempAllParameters = null;
			if (tempParam.eContainer() instanceof Test) {
				Test tempTest = (Test) tempParam.eContainer();
				tempMethod = tempTest.getDefinition().getFixtureMethod();
				tempAllParameters = tempTest.getParameters();
			} else if (tempParam.eContainer() instanceof Call) {
				Call tempCall = (Call) tempParam.eContainer();
				tempMethod = tempCall.getDefinition().getFixtureMethod();
				tempAllParameters = tempCall.getParameters();
			}

			if (tempMethod != null && isCustomProposalFixture(tempMethod)) {
				Map<String, Object> tempParamMap = IntegrityDSLUtil.createParameterMap(tempAllParameters, null, true,
						true);
				completeParameterValuesInternal(tempParam.getName(), tempMethod, tempParamMap, aContext, anAcceptor);
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeTableTestRow_Values(EObject aModel, Assignment anAssignment, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		super.completeTableTestRow_Values(aModel, anAssignment, aContext, anAcceptor);

		if (aModel instanceof TableTestRow) {
			TableTestRow tempRow = (TableTestRow) aModel;
			TableTest tempTest = (TableTest) tempRow.eContainer();
			MethodReference tempMethod = tempTest.getDefinition().getFixtureMethod();

			if (tempMethod != null) {
				EObject tempSemanticObject = NodeModelUtils.findActualSemanticObjectFor(aContext.getCurrentNode());
				int tempColumn = -1;
				if (tempSemanticObject instanceof ParameterTableValue) {
					// we're inside an empty parameter table value
					tempColumn = tempRow.getValues().indexOf(tempSemanticObject);

				} else if (tempSemanticObject == tempRow) {
					if ("".equals(aContext.getPrefix())) {
						// we're at the last (still opened and not really started) parameter table value
						tempColumn = tempRow.getValues().size();
					} else {
						// we're at the last parameter table value
						tempColumn = tempRow.getValues().size() - 1;
					}
				}

				if (tempColumn >= 0) {
					if (tempColumn < tempTest.getParameterHeaders().size()) {
						Map<String, Object> tempParamMap = IntegrityDSLUtil.createParameterMap(tempTest, tempRow, null,
								true, true);

						completeParameterValuesInternal(tempTest.getParameterHeaders().get(tempColumn).getName(),
								tempMethod, tempParamMap, aContext, anAcceptor);
					} else {
						// we might be in the range of the result columns
						int tempResultColumn = tempColumn - tempTest.getParameterHeaders().size();
						boolean tempDefaultResultExists = tempTest.getDefaultResultColumn() != null;
						if (tempResultColumn >= 0
								&& tempResultColumn < tempTest.getResultHeaders().size()
										+ (tempDefaultResultExists ? 1 : 0)) {
							try {
								ResultName tempResultName = null;
								if (tempResultColumn < tempTest.getResultHeaders().size()) {
									tempResultName = tempTest.getResultHeaders().get(tempResultColumn).getName();
								}

								ValueOrEnumValueCollection tempResultValue = null;
								if (tempColumn < tempRow.getValues().size()) {
									tempResultValue = tempRow.getValues().get(tempColumn).getValue();
								}
								Object tempConvertedResultValue = null;
								if (tempResultValue != null) {
									IJavaElement tempSourceMethod = (IJavaElement) elementFinder
											.findElementFor(tempMethod.getType());
									CompilationUnit tempCompilationUnit = (CompilationUnit) tempSourceMethod
											.getParent();
									FixtureTypeWrapper tempFixtureClassWrapper = new FixtureTypeWrapper(
											tempCompilationUnit.getTypes()[0]);

									tempConvertedResultValue = tempFixtureClassWrapper
											.convertResultValueToFixtureDefinedType(tempMethod.getMethod()
													.getSimpleName(), tempResultName, tempResultValue);

								}

								Map<String, Object> tempParamMap = IntegrityDSLUtil.createParameterMap(tempTest,
										tempRow, null, true, true);

								completeResultValuesInternal(tempResultName, tempMethod, tempConvertedResultValue,
										tempParamMap, aContext, anAcceptor);
							} catch (JavaModelException exc) {
								exc.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeParameterTableValue_Value(EObject aModel, Assignment anAssignment,
			ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {
		super.completeParameterTableValue_Value(aModel, anAssignment, aContext, anAcceptor);

		if (aModel instanceof ParameterTableValue) {
			ParameterTableValue tempParam = (ParameterTableValue) aModel;

			TableTestRow tempRow = (TableTestRow) tempParam.eContainer();
			TableTest tempTest = (TableTest) tempRow.eContainer();
			MethodReference tempMethod = tempTest.getDefinition().getFixtureMethod();

			if (tempMethod != null) {
				int tempColumn = tempRow.getValues().indexOf(tempParam);
				if (tempColumn >= 0 && tempColumn < tempTest.getParameterHeaders().size()) {
					Map<String, Object> tempParamMap = IntegrityDSLUtil.createParameterMap(tempTest, tempRow, null,
							true, true);

					completeParameterValuesInternal(tempTest.getParameterHeaders().get(tempColumn).getName(),
							tempMethod, tempParamMap, aContext, anAcceptor);
				}
			}
		}
	}

	@Override
	// SUPPRESS CHECKSTYLE MethodName
	public void completeSuiteParameter_Name(EObject aModel, Assignment anAssignment,
			final ContentAssistContext aContext, final ICompletionProposalAcceptor anAcceptor) {
		// filter out everything except suite parameters of the suite that is currently being called
		super.completeSuiteParameter_Name(aModel, anAssignment, aContext, new ICompletionProposalAcceptor() {

			@Override
			public void accept(ICompletionProposal aProposal) {
				if (aContext.getCurrentModel() instanceof Suite) {
					Suite tempCurrentSuiteCall = (Suite) aContext.getCurrentModel();
					SuiteDefinition tempCurrentSuiteDef = (tempCurrentSuiteCall).getDefinition();
					if (aProposal instanceof IntegrityConfigurableCompletionProposal) {
						SuiteDefinition tempSuiteDef = ((IntegrityConfigurableCompletionProposal) aProposal)
								.getSuiteDefiningProposedParameter();
						if (tempSuiteDef == tempCurrentSuiteDef) {

							// now filter out the ones that are already present in the call
							boolean tempAlreadyUsed = false;
							for (SuiteParameter tempAlreadyUsedParam : tempCurrentSuiteCall.getParameters()) {
								if (((IntegrityConfigurableCompletionProposal) aProposal).getReplacementString()
										.equals(tempAlreadyUsedParam.getName().getName())) {
									tempAlreadyUsed = true;
									break;
								}
							}
							if (!tempAlreadyUsed) {
								anAcceptor.accept(aProposal);
							}
						}
					}
				}
			}

			@Override
			public boolean canAcceptMoreProposals() {
				return anAcceptor.canAcceptMoreProposals();
			}

		});
	}

	private boolean isCustomProposalFixture(MethodReference aMethod) {
		for (JvmTypeReference tempRef : aMethod.getMethod().getDeclaringType().getSuperTypes()) {
			if (tempRef.getQualifiedName().equals(CustomProposalFixture.class.getName())) {
				return true;
			}
		}

		return false;
	}

	private void completeParameterValuesInternal(ParameterName aParameter, MethodReference aMethod,
			Map<String, Object> aParamMap, ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {
		try {
			IJavaElement tempSourceMethod = (IJavaElement) elementFinder.findElementFor(aMethod.getType());
			CompilationUnit tempCompilationUnit = (CompilationUnit) tempSourceMethod.getParent();
			FixtureTypeWrapper tempFixtureClassWrapper = new FixtureTypeWrapper(tempCompilationUnit.getTypes()[0]);

			CustomProposalProvider tempProposalProvider = tempFixtureClassWrapper.instantiateCustomProposalProvider();
			if (tempProposalProvider == null) {
				return;
			}

			resolveVariables(aParamMap);
			tempFixtureClassWrapper.convertParameterValuesToFixtureDefinedTypes(aMethod.getMethod().getSimpleName(),
					aParamMap, true);

			List<CustomProposalDefinition> tempProposals = tempProposalProvider.defineParameterProposals(aMethod
					.getMethod().getSimpleName(), IntegrityDSLUtil.getParamNameStringFromParameterName(aParameter),
					aParamMap);

			acceptCustomProposals(tempProposals, aContext, anAcceptor);
		} catch (JavaModelException exc) {
			exc.printStackTrace();
		}
	}

	private void completeResultValuesInternal(ResultName aResult, MethodReference aMethod, Object aResultValue,
			Map<String, Object> aParamMap, ContentAssistContext aContext, ICompletionProposalAcceptor anAcceptor) {
		try {
			IJavaElement tempSourceMethod = (IJavaElement) elementFinder.findElementFor(aMethod.getType());
			CompilationUnit tempCompilationUnit = (CompilationUnit) tempSourceMethod.getParent();
			FixtureTypeWrapper tempFixtureClassWrapper = new FixtureTypeWrapper(tempCompilationUnit.getTypes()[0]);

			CustomProposalProvider tempProposalProvider = tempFixtureClassWrapper.instantiateCustomProposalProvider();
			if (tempProposalProvider == null) {
				return;
			}

			resolveVariables(aParamMap);
			tempFixtureClassWrapper.convertParameterValuesToFixtureDefinedTypes(aMethod.getMethod().getSimpleName(),
					aParamMap, true);

			List<CustomProposalDefinition> tempProposals = tempProposalProvider.defineResultProposals(aMethod
					.getMethod().getSimpleName(),
					aResult != null ? IntegrityDSLUtil.getExpectedResultNameStringFromTestResultName(aResult) : null,
					aResultValue, aParamMap);

			acceptCustomProposals(tempProposals, aContext, anAcceptor);
		} catch (JavaModelException exc) {
			exc.printStackTrace();
		}
	}

	private void acceptCustomProposals(List<CustomProposalDefinition> someProposals, ContentAssistContext aContext,
			ICompletionProposalAcceptor anAcceptor) {
		if (someProposals == null) {
			return;
		}

		for (final CustomProposalDefinition tempProposal : someProposals) {
			ICompletionProposal tempCompletionProposal = createCompletionProposal(tempProposal.getValue(),
					new StyledString(tempProposal.getDisplayValue() != null ? tempProposal.getDisplayValue()
							: tempProposal.getValue()), null, tempProposal.getPriority() + DEFAULT_PROPOSAL_BASE,
					tempProposal.getDoPrefixFiltering() ? aContext.getPrefix() : "", aContext);
			if (tempCompletionProposal instanceof ConfigurableCompletionProposal) {
				if (tempProposal.getDescription() != null) {
					((ConfigurableCompletionProposal) tempCompletionProposal).setAdditionalProposalInfo(tempProposal
							.getDescription());
				}
			}

			anAcceptor.accept(tempCompletionProposal);
		}
	}

}