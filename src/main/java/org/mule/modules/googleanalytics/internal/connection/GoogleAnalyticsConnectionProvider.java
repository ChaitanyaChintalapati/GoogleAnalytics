package org.mule.modules.googleanalytics.internal.connection;

import org.mule.modules.googleanalytics.internal.connection.GoogleAnalyticsConnection;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.meta.model.display.PathModel.Type;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;

public class GoogleAnalyticsConnectionProvider implements CachedConnectionProvider<GoogleAnalyticsConnection> {
	
	private static final Logger log = LoggerFactory.getLogger(GoogleAnalyticsConnectionProvider.class);
	
	@Parameter
	@Path(type = Type.FILE, acceptedFileExtensions = "json", acceptsUrls = false)
	@Expression(SUPPORTED)
	private String jsonFilePath;
	
	@Parameter
	@Optional
	@Example("Hello Analytics")
	@Expression(SUPPORTED)
	String applicationName;

	@Override
	public GoogleAnalyticsConnection connect() throws GoogleAnalyticsException {
		GoogleAnalyticsConnection basicConnection = new GoogleAnalyticsConnection(jsonFilePath, applicationName);
		
		try {
			basicConnection.connect();
			if(basicConnection.getAnalytics().getApplicationName() == null) {
				
				throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file", GoogleAnalyticsError.CONNECTION_EXCEPTION);
			}
		} catch (Exception e) {
			log.error("Error occured in GoogleAnalyticsConnectionProvider::generateReport()", e);
			throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file", GoogleAnalyticsError.CONNECTION_EXCEPTION);
		}

		return basicConnection;
	}

	@Override
	public void disconnect(GoogleAnalyticsConnection connection) {
		try {
			connection.disconnect();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	@Override
	public ConnectionValidationResult validate(GoogleAnalyticsConnection connection) {
		return ConnectionValidationResult.success();
	}

}
