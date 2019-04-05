
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
        ConnectionValidationResult validationResult = connectivityTestingService.testConnection(Location.builder()
                .globalName("Google_Analytics_Config").build());

        assertThat(validationResult.isValid(), is(true));
    }
	
}

