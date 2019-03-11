package org.mule.modules.googleanalytics.internal.connection;

import static org.mule.runtime.api.connection.ConnectionValidationResult.failure;
import static org.mule.runtime.api.connection.ConnectionValidationResult.success;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.mule.modules.googleanalytics.internal.connection.GoogleAnalyticsConnection;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.util.Reference;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Example;

import com.google.api.services.analytics.Analytics;

public class OAuth2ConnectionProvider implements CachedConnectionProvider<Analytics> {
	@Parameter
	private String clientId;

	@Parameter
	private String clientSecret;

	@Parameter
	@Optional
	@Example("localhost")
	private String domain;

	@Parameter
	@Optional
	@Example("8080")
	private int port;

	@Parameter
	@Example("Hello Analytics")
	private String applicationName;

	@Override
	public Analytics connect() throws ConnectionException {
		try {
			return new GoogleAnalyticsConnection().initializeGoogleAnalytic(clientId, clientSecret, domain, port,
					applicationName);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new ConnectionException("Unable to connect to Google Analytics using OAuth");
	}

	@Override
	public void disconnect(Analytics connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public ConnectionValidationResult validate(Analytics connection) {
		Reference<ConnectionValidationResult> result = new Reference<>();
		if (connection != null && connection.metadata() != null) {
			result.set(success());
		} else {
			result.set(failure("Unable to connect to Google Analytics", new ConnectionException(
					"Unable to connect Google Analytics server. Please check your connectivity")));
		}
		return result.get();
	}

}
