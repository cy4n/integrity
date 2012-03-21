package de.gebit.integrity.experiments.fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.gebit.integrity.fixtures.CustomProposalProvider;
import de.gebit.integrity.fixtures.CustomProposalProvider.CustomProposalFixtureLink;

@CustomProposalFixtureLink(CustomProposalTestFixture.class)
public class CustomProposalTestProvider implements CustomProposalProvider {

	@Override
	public List<CustomProposalDefinition> defineProposals(
			String aFixtureMethodName, String aParameterName,
			Map<String, Object> someParameterValues) {
		List<CustomProposalDefinition> tempResults = new ArrayList<CustomProposalDefinition>();
		tempResults.add(new CustomProposalDefinition("blahblub",aFixtureMethodName + "|" + aParameterName, someParameterValues.toString(), 0, false));
		
		return tempResults;
	}

}
