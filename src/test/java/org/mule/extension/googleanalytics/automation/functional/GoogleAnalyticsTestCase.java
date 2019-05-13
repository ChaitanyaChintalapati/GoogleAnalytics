
/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.extension.googleanalytics.automation.functional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.mule.extension.googleanalytics.automation.functional.TestDataBuilder.getAutomationCredentialsProperties;

import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public class GoogleAnalyticsTestCase extends MuleArtifactFunctionalTestCase {

	Properties GoogleAnalyticsProperties;

	@Override
	protected String getConfigFile() {
		return "test-google-analytics-connector.xml";
	}

	@Before
	public void initialSetup() throws Exception {
		GoogleAnalyticsProperties = getAutomationCredentialsProperties();
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
