/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.extension.googleanalytics.automation.system;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mule.runtime.api.connectivity.ConnectivityTestingService.CONNECTIVITY_TESTING_SERVICE_KEY;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.mule.extension.googleanalytics.automation.functional.GoogleAnalyticsTestCase;
import org.mule.runtime.api.component.location.Location;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connectivity.ConnectivityTestingService;

public class ConfigTestCase extends GoogleAnalyticsTestCase {

	@Inject
	@Named(CONNECTIVITY_TESTING_SERVICE_KEY)
	ConnectivityTestingService connectivityTestingService;

	@Test
	public void connectWithBasicParamsTest() {
		ConnectionValidationResult validationResult = connectivityTestingService
				.testConnection(Location.builder().globalName("Google_Analytics_Config").build());

		assertThat(validationResult.isValid(), is(true));
	}

}
