/**
 * <copyright>
 * </copyright>
 *

 */
package de.integrity.dsl.impl;

import de.integrity.dsl.DslPackage;
import de.integrity.dsl.FixedTestResultName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xtext.common.types.JvmField;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fixed Test Result Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.integrity.dsl.impl.FixedTestResultNameImpl#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixedTestResultNameImpl extends TestResultNameImpl implements FixedTestResultName
{
  /**
   * The cached value of the '{@link #getField() <em>Field</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getField()
   * @generated
   * @ordered
   */
  protected JvmField field;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FixedTestResultNameImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return DslPackage.Literals.FIXED_TEST_RESULT_NAME;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmField getField()
  {
    if (field != null && field.eIsProxy())
    {
      InternalEObject oldField = (InternalEObject)field;
      field = (JvmField)eResolveProxy(oldField);
      if (field != oldField)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DslPackage.FIXED_TEST_RESULT_NAME__FIELD, oldField, field));
      }
    }
    return field;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmField basicGetField()
  {
    return field;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setField(JvmField newField)
  {
    JvmField oldField = field;
    field = newField;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FIXED_TEST_RESULT_NAME__FIELD, oldField, field));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DslPackage.FIXED_TEST_RESULT_NAME__FIELD:
        if (resolve) return getField();
        return basicGetField();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DslPackage.FIXED_TEST_RESULT_NAME__FIELD:
        setField((JvmField)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.FIXED_TEST_RESULT_NAME__FIELD:
        setField((JvmField)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.FIXED_TEST_RESULT_NAME__FIELD:
        return field != null;
    }
    return super.eIsSet(featureID);
  }

} //FixedTestResultNameImpl
