/**
 */
package de.gebit.integrity.dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result Table Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.gebit.integrity.dsl.ResultTableHeader#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see de.gebit.integrity.dsl.DslPackage#getResultTableHeader()
 * @model
 * @generated
 */
public interface ResultTableHeader extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ResultName)
   * @see de.gebit.integrity.dsl.DslPackage#getResultTableHeader_Name()
   * @model containment="true"
   * @generated
   */
  ResultName getName();

  /**
   * Sets the value of the '{@link de.gebit.integrity.dsl.ResultTableHeader#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ResultName value);

} // ResultTableHeader
