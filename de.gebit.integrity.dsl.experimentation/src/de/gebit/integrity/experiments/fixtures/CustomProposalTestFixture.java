package de.gebit.integrity.experiments.fixtures;

import de.gebit.integrity.fixtures.CustomProposalFixture;
import de.gebit.integrity.fixtures.FixtureMethod;
import de.gebit.integrity.fixtures.FixtureParameter;

public class CustomProposalTestFixture implements CustomProposalFixture {

	@FixtureMethod()
	public void dummyMethod(@FixtureParameter(name = "param1") String aParam1, @FixtureParameter(name = "param2") String aParam2) {
		
	}
	
}
