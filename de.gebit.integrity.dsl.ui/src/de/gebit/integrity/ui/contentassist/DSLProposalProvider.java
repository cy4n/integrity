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
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.common.types.util.jdt.IJavaElementFinder;
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
import de.gebit.integrity.dsl.ParameterTableHeader;
import de.gebit.integrity.dsl.ResultTableHeader;
import de.gebit.integrity.dsl.TableTest;
import de.gebit.integrity.dsl.Test;
import de.gebit.integrity.dsl.TestDefinition;
import de.gebit.integrity.dsl.Variable;
import de.gebit.integrity.fixtures.ArbitraryParameterFixture;
import de.gebit.integrity.fixtures.ArbitraryParameterFixture.ArbitraryParameterDefinition;
import de.gebit.integrity.fixtures.Fixture;
import de.gebit.integrity.ui.utils.ClassLoadingUtil;
import de.gebit.integrity.utils.IntegrityDSLUtil;
import de.gebit.integrity.utils.JavadocUtil;
import de.gebit.integrity.utils.ParamAnnotationTuple;

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

		TableTest tempTableTest = (TableTest) aModel;
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
			completeParametersInternal(tempAlreadyUsedParameters, tempTestDef.getFixtureMethod(), "| ", aContext,
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
			Class<?> tempFixtureClass;
			try {
				tempFixtureClass = ClassLoadingUtil.loadClassFromWorkspace(
						tempCompilationUnit.getTypes()[0].getFullyQualifiedName(), tempSourceMethod.getJavaProject());
				Fixture tempFixtureInstance = (Fixture) tempFixtureClass.newInstance();

				if (tempFixtureInstance instanceof ArbitraryParameterFixture) {
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

					// since we're inside eclipse here, we must resolve variables and constants "by hand" here to their
					// actual values
					for (Entry<String, Object> tempEntry : tempFixedParameterMap.entrySet()) {
						if (tempEntry.getValue() instanceof Variable) {
							tempEntry.setValue(IntegrityDSLUtil.resolveVariableStatically((Variable) tempEntry
									.getValue()));
						}
					}

					tempFixtureInstance.convertParameterValuesToFixtureDefinedTypes(Fixture.findFixtureMethodByName(
							tempFixtureClass, tempMethodReference.getMethod().getSimpleName()), tempFixedParameterMap,
							false, true);

					// first fetch the arbitrary parameter names...
					List<ArbitraryParameterDefinition> tempParameterDescriptions = ((ArbitraryParameterFixture) tempFixtureInstance)
							.defineArbitraryParameters(tempMethodReference.getMethod().getSimpleName(),
									tempFixedParameterMap, true);
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
						List<ArbitraryParameterDefinition> tempResultDescriptions = ((ArbitraryParameterFixture) tempFixtureInstance)
								.defineArbitraryResults(tempMethodReference.getMethod().getSimpleName(),
										tempFixedParameterMap, true);
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
			} catch (ClassNotFoundException exc) {
				exc.printStackTrace();
			} catch (InstantiationException exc) {
				exc.printStackTrace();
			} catch (IllegalAccessException exc) {
				exc.printStackTrace();
			} catch (JavaModelException exc) {
				exc.printStackTrace();
			}
		}
	}

}
