/**
 */
package dk.dtu.compute.mbse.petrinet.tests;

import dk.dtu.compute.mbse.petrinet.Petrinet;
import dk.dtu.compute.mbse.petrinet.PetrinetFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Petrinet</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PetrinetTest extends TestCase {

	/**
	 * The fixture for this Petrinet test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Petrinet fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PetrinetTest.class);
	}

	/**
	 * Constructs a new Petrinet test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PetrinetTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Petrinet test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Petrinet fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Petrinet test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Petrinet getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(PetrinetFactory.eINSTANCE.createPetrinet());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //PetrinetTest
