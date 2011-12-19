/**
 * <copyright>
 * </copyright>
 *

 */
package de.integrity.dsl.impl;

import de.integrity.dsl.ArbitraryParameterName;
import de.integrity.dsl.ArbitraryTestResultName;
import de.integrity.dsl.Call;
import de.integrity.dsl.CallDefinition;
import de.integrity.dsl.DecimalValue;
import de.integrity.dsl.DslFactory;
import de.integrity.dsl.DslPackage;
import de.integrity.dsl.EnumValue;
import de.integrity.dsl.FixedParameterName;
import de.integrity.dsl.FixedTestResultName;
import de.integrity.dsl.Import;
import de.integrity.dsl.IntegerValue;
import de.integrity.dsl.MethodReference;
import de.integrity.dsl.Model;
import de.integrity.dsl.NamedTestResult;
import de.integrity.dsl.PackageDefinition;
import de.integrity.dsl.PackageStatement;
import de.integrity.dsl.Parameter;
import de.integrity.dsl.ParameterName;
import de.integrity.dsl.ParameterTableHeader;
import de.integrity.dsl.ParameterTableValue;
import de.integrity.dsl.Statement;
import de.integrity.dsl.StringValue;
import de.integrity.dsl.Suite;
import de.integrity.dsl.SuiteDefinition;
import de.integrity.dsl.SuiteParameter;
import de.integrity.dsl.SuiteStatement;
import de.integrity.dsl.SuiteStatementWithResult;
import de.integrity.dsl.TableTest;
import de.integrity.dsl.TableTestRow;
import de.integrity.dsl.Test;
import de.integrity.dsl.TestDefinition;
import de.integrity.dsl.TestResultName;
import de.integrity.dsl.Value;
import de.integrity.dsl.ValueOrEnumValue;
import de.integrity.dsl.Variable;
import de.integrity.dsl.VariableDefinition;
import de.integrity.dsl.VariableEntity;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xtext.common.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DslPackageImpl extends EPackageImpl implements DslPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass packageDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass packageStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass importEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass callDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suiteDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suiteStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suiteStatementWithResultEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tableTestEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tableTestRowEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterTableHeaderEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterTableValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedTestResultEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testResultNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fixedTestResultNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arbitraryTestResultNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass callEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suiteEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suiteParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fixedParameterNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arbitraryParameterNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueOrEnumValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass integerValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass decimalValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass methodReferenceEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see de.integrity.dsl.DslPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DslPackageImpl()
  {
    super(eNS_URI, DslFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link DslPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static DslPackage init()
  {
    if (isInited) return (DslPackage)EPackage.Registry.INSTANCE.getEPackage(DslPackage.eNS_URI);

    // Obtain or create and register package
    DslPackageImpl theDslPackage = (DslPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DslPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DslPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    TypesPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theDslPackage.createPackageContents();

    // Initialize created meta-data
    theDslPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDslPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DslPackage.eNS_URI, theDslPackage);
    return theDslPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModel()
  {
    return modelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModel_Statements()
  {
    return (EReference)modelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStatement()
  {
    return statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackageDefinition()
  {
    return packageDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageDefinition_Name()
  {
    return (EAttribute)packageDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPackageDefinition_Statements()
  {
    return (EReference)packageDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackageStatement()
  {
    return packageStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImport()
  {
    return importEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImport_ImportedNamespace()
  {
    return (EAttribute)importEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTestDefinition()
  {
    return testDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTestDefinition_Name()
  {
    return (EAttribute)testDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTestDefinition_FixtureMethod()
  {
    return (EReference)testDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCallDefinition()
  {
    return callDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCallDefinition_Name()
  {
    return (EAttribute)callDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCallDefinition_FixtureMethod()
  {
    return (EReference)callDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSuiteDefinition()
  {
    return suiteDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSuiteDefinition_Name()
  {
    return (EAttribute)suiteDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuiteDefinition_Parameters()
  {
    return (EReference)suiteDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuiteDefinition_Dependencies()
  {
    return (EReference)suiteDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuiteDefinition_Statements()
  {
    return (EReference)suiteDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuiteDefinition_Finalizers()
  {
    return (EReference)suiteDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSuiteStatement()
  {
    return suiteStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSuiteStatementWithResult()
  {
    return suiteStatementWithResultEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableDefinition()
  {
    return variableDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariableDefinition_Name()
  {
    return (EReference)variableDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariableDefinition_InitialValue()
  {
    return (EReference)variableDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableEntity()
  {
    return variableEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableEntity_Name()
  {
    return (EAttribute)variableEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTest()
  {
    return testEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTest_Definition()
  {
    return (EReference)testEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTest_Parameters()
  {
    return (EReference)testEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTest_Results()
  {
    return (EReference)testEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTest_Result()
  {
    return (EReference)testEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTableTest()
  {
    return tableTestEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTableTest_Definition()
  {
    return (EReference)tableTestEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTableTest_Parameters()
  {
    return (EReference)tableTestEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTableTest_Headers()
  {
    return (EReference)tableTestEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTableTest_Rows()
  {
    return (EReference)tableTestEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTableTestRow()
  {
    return tableTestRowEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTableTestRow_Values()
  {
    return (EReference)tableTestRowEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTableTestRow_Result()
  {
    return (EReference)tableTestRowEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterTableHeader()
  {
    return parameterTableHeaderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterTableHeader_Name()
  {
    return (EReference)parameterTableHeaderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterTableValue()
  {
    return parameterTableValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterTableValue_Value()
  {
    return (EReference)parameterTableValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamedTestResult()
  {
    return namedTestResultEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamedTestResult_Name()
  {
    return (EReference)namedTestResultEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamedTestResult_Value()
  {
    return (EReference)namedTestResultEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTestResultName()
  {
    return testResultNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFixedTestResultName()
  {
    return fixedTestResultNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFixedTestResultName_Field()
  {
    return (EReference)fixedTestResultNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArbitraryTestResultName()
  {
    return arbitraryTestResultNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getArbitraryTestResultName_Identifier()
  {
    return (EAttribute)arbitraryTestResultNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCall()
  {
    return callEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCall_Definition()
  {
    return (EReference)callEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCall_Parameters()
  {
    return (EReference)callEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCall_Result()
  {
    return (EReference)callEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSuite()
  {
    return suiteEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuite_Definition()
  {
    return (EReference)suiteEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuite_Parameters()
  {
    return (EReference)suiteEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSuiteParameter()
  {
    return suiteParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuiteParameter_Name()
  {
    return (EReference)suiteParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSuiteParameter_Value()
  {
    return (EReference)suiteParameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameter()
  {
    return parameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameter_Name()
  {
    return (EReference)parameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameter_Value()
  {
    return (EReference)parameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterName()
  {
    return parameterNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFixedParameterName()
  {
    return fixedParameterNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFixedParameterName_Annotation()
  {
    return (EReference)fixedParameterNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArbitraryParameterName()
  {
    return arbitraryParameterNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getArbitraryParameterName_Identifier()
  {
    return (EAttribute)arbitraryParameterNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValueOrEnumValue()
  {
    return valueOrEnumValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValue()
  {
    return valueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntegerValue()
  {
    return integerValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntegerValue_IntegerValue()
  {
    return (EAttribute)integerValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDecimalValue()
  {
    return decimalValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDecimalValue_DecimalValue()
  {
    return (EAttribute)decimalValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringValue()
  {
    return stringValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStringValue_StringValue()
  {
    return (EAttribute)stringValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariable()
  {
    return variableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariable_Name()
  {
    return (EReference)variableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumValue()
  {
    return enumValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumValue_EnumValue()
  {
    return (EReference)enumValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMethodReference()
  {
    return methodReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMethodReference_Type()
  {
    return (EReference)methodReferenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMethodReference_Method()
  {
    return (EReference)methodReferenceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DslFactory getDslFactory()
  {
    return (DslFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modelEClass = createEClass(MODEL);
    createEReference(modelEClass, MODEL__STATEMENTS);

    statementEClass = createEClass(STATEMENT);

    packageDefinitionEClass = createEClass(PACKAGE_DEFINITION);
    createEAttribute(packageDefinitionEClass, PACKAGE_DEFINITION__NAME);
    createEReference(packageDefinitionEClass, PACKAGE_DEFINITION__STATEMENTS);

    packageStatementEClass = createEClass(PACKAGE_STATEMENT);

    importEClass = createEClass(IMPORT);
    createEAttribute(importEClass, IMPORT__IMPORTED_NAMESPACE);

    testDefinitionEClass = createEClass(TEST_DEFINITION);
    createEAttribute(testDefinitionEClass, TEST_DEFINITION__NAME);
    createEReference(testDefinitionEClass, TEST_DEFINITION__FIXTURE_METHOD);

    callDefinitionEClass = createEClass(CALL_DEFINITION);
    createEAttribute(callDefinitionEClass, CALL_DEFINITION__NAME);
    createEReference(callDefinitionEClass, CALL_DEFINITION__FIXTURE_METHOD);

    suiteDefinitionEClass = createEClass(SUITE_DEFINITION);
    createEAttribute(suiteDefinitionEClass, SUITE_DEFINITION__NAME);
    createEReference(suiteDefinitionEClass, SUITE_DEFINITION__PARAMETERS);
    createEReference(suiteDefinitionEClass, SUITE_DEFINITION__DEPENDENCIES);
    createEReference(suiteDefinitionEClass, SUITE_DEFINITION__STATEMENTS);
    createEReference(suiteDefinitionEClass, SUITE_DEFINITION__FINALIZERS);

    suiteStatementEClass = createEClass(SUITE_STATEMENT);

    suiteStatementWithResultEClass = createEClass(SUITE_STATEMENT_WITH_RESULT);

    variableDefinitionEClass = createEClass(VARIABLE_DEFINITION);
    createEReference(variableDefinitionEClass, VARIABLE_DEFINITION__NAME);
    createEReference(variableDefinitionEClass, VARIABLE_DEFINITION__INITIAL_VALUE);

    variableEntityEClass = createEClass(VARIABLE_ENTITY);
    createEAttribute(variableEntityEClass, VARIABLE_ENTITY__NAME);

    testEClass = createEClass(TEST);
    createEReference(testEClass, TEST__DEFINITION);
    createEReference(testEClass, TEST__PARAMETERS);
    createEReference(testEClass, TEST__RESULTS);
    createEReference(testEClass, TEST__RESULT);

    tableTestEClass = createEClass(TABLE_TEST);
    createEReference(tableTestEClass, TABLE_TEST__DEFINITION);
    createEReference(tableTestEClass, TABLE_TEST__PARAMETERS);
    createEReference(tableTestEClass, TABLE_TEST__HEADERS);
    createEReference(tableTestEClass, TABLE_TEST__ROWS);

    tableTestRowEClass = createEClass(TABLE_TEST_ROW);
    createEReference(tableTestRowEClass, TABLE_TEST_ROW__VALUES);
    createEReference(tableTestRowEClass, TABLE_TEST_ROW__RESULT);

    parameterTableHeaderEClass = createEClass(PARAMETER_TABLE_HEADER);
    createEReference(parameterTableHeaderEClass, PARAMETER_TABLE_HEADER__NAME);

    parameterTableValueEClass = createEClass(PARAMETER_TABLE_VALUE);
    createEReference(parameterTableValueEClass, PARAMETER_TABLE_VALUE__VALUE);

    namedTestResultEClass = createEClass(NAMED_TEST_RESULT);
    createEReference(namedTestResultEClass, NAMED_TEST_RESULT__NAME);
    createEReference(namedTestResultEClass, NAMED_TEST_RESULT__VALUE);

    testResultNameEClass = createEClass(TEST_RESULT_NAME);

    fixedTestResultNameEClass = createEClass(FIXED_TEST_RESULT_NAME);
    createEReference(fixedTestResultNameEClass, FIXED_TEST_RESULT_NAME__FIELD);

    arbitraryTestResultNameEClass = createEClass(ARBITRARY_TEST_RESULT_NAME);
    createEAttribute(arbitraryTestResultNameEClass, ARBITRARY_TEST_RESULT_NAME__IDENTIFIER);

    callEClass = createEClass(CALL);
    createEReference(callEClass, CALL__DEFINITION);
    createEReference(callEClass, CALL__PARAMETERS);
    createEReference(callEClass, CALL__RESULT);

    suiteEClass = createEClass(SUITE);
    createEReference(suiteEClass, SUITE__DEFINITION);
    createEReference(suiteEClass, SUITE__PARAMETERS);

    suiteParameterEClass = createEClass(SUITE_PARAMETER);
    createEReference(suiteParameterEClass, SUITE_PARAMETER__NAME);
    createEReference(suiteParameterEClass, SUITE_PARAMETER__VALUE);

    parameterEClass = createEClass(PARAMETER);
    createEReference(parameterEClass, PARAMETER__NAME);
    createEReference(parameterEClass, PARAMETER__VALUE);

    parameterNameEClass = createEClass(PARAMETER_NAME);

    fixedParameterNameEClass = createEClass(FIXED_PARAMETER_NAME);
    createEReference(fixedParameterNameEClass, FIXED_PARAMETER_NAME__ANNOTATION);

    arbitraryParameterNameEClass = createEClass(ARBITRARY_PARAMETER_NAME);
    createEAttribute(arbitraryParameterNameEClass, ARBITRARY_PARAMETER_NAME__IDENTIFIER);

    valueOrEnumValueEClass = createEClass(VALUE_OR_ENUM_VALUE);

    valueEClass = createEClass(VALUE);

    integerValueEClass = createEClass(INTEGER_VALUE);
    createEAttribute(integerValueEClass, INTEGER_VALUE__INTEGER_VALUE);

    decimalValueEClass = createEClass(DECIMAL_VALUE);
    createEAttribute(decimalValueEClass, DECIMAL_VALUE__DECIMAL_VALUE);

    stringValueEClass = createEClass(STRING_VALUE);
    createEAttribute(stringValueEClass, STRING_VALUE__STRING_VALUE);

    variableEClass = createEClass(VARIABLE);
    createEReference(variableEClass, VARIABLE__NAME);

    enumValueEClass = createEClass(ENUM_VALUE);
    createEReference(enumValueEClass, ENUM_VALUE__ENUM_VALUE);

    methodReferenceEClass = createEClass(METHOD_REFERENCE);
    createEReference(methodReferenceEClass, METHOD_REFERENCE__TYPE);
    createEReference(methodReferenceEClass, METHOD_REFERENCE__METHOD);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    packageDefinitionEClass.getESuperTypes().add(this.getStatement());
    importEClass.getESuperTypes().add(this.getStatement());
    importEClass.getESuperTypes().add(this.getPackageStatement());
    testDefinitionEClass.getESuperTypes().add(this.getPackageStatement());
    callDefinitionEClass.getESuperTypes().add(this.getPackageStatement());
    suiteDefinitionEClass.getESuperTypes().add(this.getPackageStatement());
    suiteStatementWithResultEClass.getESuperTypes().add(this.getSuiteStatement());
    variableDefinitionEClass.getESuperTypes().add(this.getPackageStatement());
    variableDefinitionEClass.getESuperTypes().add(this.getSuiteStatement());
    testEClass.getESuperTypes().add(this.getSuiteStatementWithResult());
    tableTestEClass.getESuperTypes().add(this.getSuiteStatementWithResult());
    fixedTestResultNameEClass.getESuperTypes().add(this.getTestResultName());
    arbitraryTestResultNameEClass.getESuperTypes().add(this.getTestResultName());
    callEClass.getESuperTypes().add(this.getSuiteStatement());
    suiteEClass.getESuperTypes().add(this.getStatement());
    suiteEClass.getESuperTypes().add(this.getSuiteStatementWithResult());
    fixedParameterNameEClass.getESuperTypes().add(this.getParameterName());
    arbitraryParameterNameEClass.getESuperTypes().add(this.getParameterName());
    valueEClass.getESuperTypes().add(this.getValueOrEnumValue());
    integerValueEClass.getESuperTypes().add(this.getValue());
    decimalValueEClass.getESuperTypes().add(this.getValue());
    stringValueEClass.getESuperTypes().add(this.getValue());
    variableEClass.getESuperTypes().add(this.getValue());
    enumValueEClass.getESuperTypes().add(this.getValueOrEnumValue());

    // Initialize classes and features; add operations and parameters
    initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModel_Statements(), this.getStatement(), null, "statements", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(statementEClass, Statement.class, "Statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(packageDefinitionEClass, PackageDefinition.class, "PackageDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPackageDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, PackageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackageDefinition_Statements(), this.getPackageStatement(), null, "statements", null, 0, -1, PackageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageStatementEClass, PackageStatement.class, "PackageStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImport_ImportedNamespace(), ecorePackage.getEString(), "importedNamespace", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testDefinitionEClass, TestDefinition.class, "TestDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTestDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTestDefinition_FixtureMethod(), this.getMethodReference(), null, "fixtureMethod", null, 0, 1, TestDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(callDefinitionEClass, CallDefinition.class, "CallDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCallDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, CallDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCallDefinition_FixtureMethod(), this.getMethodReference(), null, "fixtureMethod", null, 0, 1, CallDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suiteDefinitionEClass, SuiteDefinition.class, "SuiteDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSuiteDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, SuiteDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSuiteDefinition_Parameters(), this.getVariableEntity(), null, "parameters", null, 0, -1, SuiteDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSuiteDefinition_Dependencies(), this.getSuiteDefinition(), null, "dependencies", null, 0, -1, SuiteDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSuiteDefinition_Statements(), this.getSuiteStatement(), null, "statements", null, 0, -1, SuiteDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSuiteDefinition_Finalizers(), this.getSuiteDefinition(), null, "finalizers", null, 0, -1, SuiteDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suiteStatementEClass, SuiteStatement.class, "SuiteStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(suiteStatementWithResultEClass, SuiteStatementWithResult.class, "SuiteStatementWithResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(variableDefinitionEClass, VariableDefinition.class, "VariableDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableDefinition_Name(), this.getVariableEntity(), null, "name", null, 0, 1, VariableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariableDefinition_InitialValue(), this.getValue(), null, "initialValue", null, 0, 1, VariableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableEntityEClass, VariableEntity.class, "VariableEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVariableEntity_Name(), ecorePackage.getEString(), "name", null, 0, 1, VariableEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testEClass, Test.class, "Test", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTest_Definition(), this.getTestDefinition(), null, "definition", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTest_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTest_Results(), this.getNamedTestResult(), null, "results", null, 0, -1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTest_Result(), this.getValueOrEnumValue(), null, "result", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tableTestEClass, TableTest.class, "TableTest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTableTest_Definition(), this.getTestDefinition(), null, "definition", null, 0, 1, TableTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTableTest_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, TableTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTableTest_Headers(), this.getParameterTableHeader(), null, "headers", null, 0, -1, TableTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTableTest_Rows(), this.getTableTestRow(), null, "rows", null, 0, -1, TableTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tableTestRowEClass, TableTestRow.class, "TableTestRow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTableTestRow_Values(), this.getParameterTableValue(), null, "values", null, 0, -1, TableTestRow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTableTestRow_Result(), this.getValueOrEnumValue(), null, "result", null, 0, 1, TableTestRow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterTableHeaderEClass, ParameterTableHeader.class, "ParameterTableHeader", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameterTableHeader_Name(), this.getParameterName(), null, "name", null, 0, 1, ParameterTableHeader.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterTableValueEClass, ParameterTableValue.class, "ParameterTableValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameterTableValue_Value(), this.getValueOrEnumValue(), null, "value", null, 0, 1, ParameterTableValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namedTestResultEClass, NamedTestResult.class, "NamedTestResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNamedTestResult_Name(), this.getTestResultName(), null, "name", null, 0, 1, NamedTestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamedTestResult_Value(), this.getValueOrEnumValue(), null, "value", null, 0, 1, NamedTestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testResultNameEClass, TestResultName.class, "TestResultName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(fixedTestResultNameEClass, FixedTestResultName.class, "FixedTestResultName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFixedTestResultName_Field(), theTypesPackage.getJvmField(), null, "field", null, 0, 1, FixedTestResultName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(arbitraryTestResultNameEClass, ArbitraryTestResultName.class, "ArbitraryTestResultName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getArbitraryTestResultName_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ArbitraryTestResultName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(callEClass, Call.class, "Call", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCall_Definition(), this.getCallDefinition(), null, "definition", null, 0, 1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCall_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCall_Result(), this.getVariable(), null, "result", null, 0, 1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suiteEClass, Suite.class, "Suite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSuite_Definition(), this.getSuiteDefinition(), null, "definition", null, 0, 1, Suite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSuite_Parameters(), this.getSuiteParameter(), null, "parameters", null, 0, -1, Suite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suiteParameterEClass, SuiteParameter.class, "SuiteParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSuiteParameter_Name(), this.getVariableEntity(), null, "name", null, 0, 1, SuiteParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSuiteParameter_Value(), this.getValue(), null, "value", null, 0, 1, SuiteParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameter_Name(), this.getParameterName(), null, "name", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameter_Value(), this.getValueOrEnumValue(), null, "value", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterNameEClass, ParameterName.class, "ParameterName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(fixedParameterNameEClass, FixedParameterName.class, "FixedParameterName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFixedParameterName_Annotation(), theTypesPackage.getJvmAnnotationReference(), null, "annotation", null, 0, 1, FixedParameterName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(arbitraryParameterNameEClass, ArbitraryParameterName.class, "ArbitraryParameterName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getArbitraryParameterName_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ArbitraryParameterName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(valueOrEnumValueEClass, ValueOrEnumValue.class, "ValueOrEnumValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(valueEClass, Value.class, "Value", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(integerValueEClass, IntegerValue.class, "IntegerValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntegerValue_IntegerValue(), ecorePackage.getEBigInteger(), "integerValue", null, 0, 1, IntegerValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(decimalValueEClass, DecimalValue.class, "DecimalValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDecimalValue_DecimalValue(), ecorePackage.getEBigDecimal(), "decimalValue", null, 0, 1, DecimalValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(stringValueEClass, StringValue.class, "StringValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStringValue_StringValue(), ecorePackage.getEString(), "stringValue", null, 0, 1, StringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariable_Name(), this.getVariableEntity(), null, "name", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumValueEClass, EnumValue.class, "EnumValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumValue_EnumValue(), theTypesPackage.getJvmEnumerationLiteral(), null, "enumValue", null, 0, 1, EnumValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(methodReferenceEClass, MethodReference.class, "MethodReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMethodReference_Type(), theTypesPackage.getJvmType(), null, "type", null, 0, 1, MethodReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMethodReference_Method(), theTypesPackage.getJvmOperation(), null, "method", null, 0, 1, MethodReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //DslPackageImpl
