/**
 * <copyright>
 * </copyright>
 *

 */
package de.integrity.dsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.integrity.dsl.Variable#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.integrity.dsl.DslPackage#getVariable()
 * @model
 * @generated
 */
public interface Variable extends Value
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' reference.
   * @see #setName(VariableEntity)
   * @see de.integrity.dsl.DslPackage#getVariable_Name()
   * @model
   * @generated
   */
  VariableEntity getName();

  /**
   * Sets the value of the '{@link de.integrity.dsl.Variable#getName <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' reference.
   * @see #getName()
   * @generated
   */
  void setName(VariableEntity value);

} // Variable
