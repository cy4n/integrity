/**
 * 
 */
package de.gebit.integrity.bindings.swing.basic;

import javax.swing.JButton;

import de.gebit.integrity.bindings.swing.AbstractSwingFixture;
import de.gebit.integrity.fixtures.CustomProposalFixture;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

/**
 * 
 * 
 * @author Rene Schneider
 * 
 */
public class SwingButtonFixture extends AbstractSwingFixture implements CustomProposalFixture {

	@FixtureMethod(description = "Click the button $name$")
	public void clickButton(@FixtureParameter(name = COMPONENT_PATH_PARAMETER_NAME) String aComponentPath) {
		JButton tempButton = (JButton) findComponentGuarded(aComponentPath, JButton.class, null);

		tempButton.doClick();

		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException exc) {
		// // TODO Auto-generated catch block
		// exc.printStackTrace();
		// }
	}

}
