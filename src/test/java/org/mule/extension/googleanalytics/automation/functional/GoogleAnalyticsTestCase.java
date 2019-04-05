package org.mule.extension.googleanalytics.automation.functional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;


import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import static org.mule.extension.googleanalytics.automation.functional.TestDataBuilder.getAutomationCredentialsProperties;
public class GoogleAnalyticsTestCase extends MuleArtifactFunctionalTestCase {

	Properties GoogleAnalyticsProperties;
	
	@Override
	protected String getConfigFile() {
		return "test-google-analytics-connector.xml";
	}
	
	@Before
    public void initialSetup() throws Exception {
		GoogleAnalyticsProperties  = getAutomationCredentialsProperties();
}
	
	@Test
	public void executeGenerateReportOperation() throws Exception {
		String payloadValue = ((String) flowRunner("generateReportFlow").run().getMessage().getPayload().getValue());

		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> jsonObject = mapper.readValue(payloadValue, Map.class);

		int result = (int) jsonObject.get("totalResults");

		assertThat(result, is(greaterThan(0)));

	}
}
