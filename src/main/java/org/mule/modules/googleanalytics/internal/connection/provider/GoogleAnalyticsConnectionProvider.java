/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.googleanalytics.internal.connection.provider;

import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;

import org.mule.modules.googleanalytics.internal.connection.GoogleAnalyticsConnection;
import org.mule.modules.googleanalytics.internal.error.GoogleAnalyticsErrorType;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.meta.model.display.PathModel.Type;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class GoogleAnalyticsConnectionProvider implements CachedConnectionProvider<GoogleAnalyticsConnection>  {

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
			if (basicConnection.getAnalytics().getApplicationName() == null) {
				throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file",
						GoogleAnalyticsErrorType.CONNECTION_EXCEPTION);
			}
		} catch (Exception e) {
			log.error("Error occured in GoogleAnalyticsConnectionProvider::generateReport()", e);
			throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file",
					GoogleAnalyticsErrorType.CONNECTION_EXCEPTION);
		}

		return basicConnection;
	}

	@Override
	public void disconnect(GoogleAnalyticsConnection connection) {
		try {
			connection.disconnect();
		} catch (Exception e) {
			log.error("Error occured in GoogleAnalyticsConnectionProvider::generateReport()", e);
			throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file",
					GoogleAnalyticsErrorType.CONNECTION_EXCEPTION);
		}
	}

	@Override
	public ConnectionValidationResult validate(GoogleAnalyticsConnection connection) {
		return ConnectionValidationResult.success();
	}
}
