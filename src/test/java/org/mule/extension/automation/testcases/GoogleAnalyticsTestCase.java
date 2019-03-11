
package org.mule.extension.automation.testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class GoogleAnalyticsTestCase extends MuleArtifactFunctionalTestCase {

	@Override
	protected String getConfigFile() {
		return "test-google-analytics-connector.xml";
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
