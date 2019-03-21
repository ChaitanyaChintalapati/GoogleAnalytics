package org.mule.modules.googleanalytics.internal.connection;

import static org.mule.runtime.api.connection.ConnectionValidationResult.failure;
import static org.mule.runtime.api.connection.ConnectionValidationResult.success;

import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.util.Reference;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.exception.ModuleException;

import com.google.api.services.analytics.Analytics;

public class OAuth2ConnectionProvider implements CachedConnectionProvider<Analytics> {
	/**
     * Client ID
     */
	@Parameter
	private String clientId;
	
	
	/**
     * Client Secret
     */
	@Parameter
	private String clientSecret;

	
	/**
     * Host name or IP address
     */
	@Parameter
	@Optional
	@Example("localhost")
	private String domain;

	
	/**
     * Port
     */
	@Parameter
	@Optional
	@Example("8080")
	private int port;
	
	/**
     * Application Name
     */

	@Parameter
	@Example("Hello Analytics")
	private String applicationName;

	@Override
	public Analytics connect()  {

			
					return new GoogleAnalyticsConnection().initializeGoogleAnalytic(clientId, clientSecret, domain, port,
							applicationName);
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
			
			
	result.set(failure("Unable to connect to Google Analytics server. " , new ConnectionException(new ModuleException("Unable to connect to Google Analytics Server. ", GoogleAnalyticsError.INVALID_AUTH))));
		}
		return result.get();
	}

}
