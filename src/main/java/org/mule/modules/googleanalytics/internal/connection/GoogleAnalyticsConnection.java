/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.googleanalytics.internal.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.mule.modules.googleanalytics.internal.error.GoogleAnalyticsErrorType;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class GoogleAnalyticsConnection {

	private static final Logger log = LoggerFactory.getLogger(GoogleAnalyticsConnection.class);

	private Analytics analytics;
	private String jsonFilePath;
	private String applicationName;
	
	

	public GoogleAnalyticsConnection(String jsonFilePath, String applicationName) {
		this.jsonFilePath = jsonFilePath;
		this.applicationName = applicationName;
	}

	// method which provide google Analytics connection
	public void connect() throws GoogleAnalyticsException {

		InputStream path = GoogleAnalyticsConnection.class.getClassLoader().getResourceAsStream(jsonFilePath);
		
		if(path == null) {
			try {
				path = new FileInputStream(jsonFilePath);
			} catch (Exception e) {
				log.error("Error occured in GoogleAnalyticsConnection::generateReport()", e);
				throw new GoogleAnalyticsException("Unable to load provided json file path : " + jsonFilePath,
						GoogleAnalyticsErrorType.CONNECTION_EXCEPTION);
			}
		}

		JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
		HttpTransport httpTransport;

		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e) {
			log.error("Error occured in GoogleAnalyticsConnection::generateReport()", e);
			throw new GoogleAnalyticsException("Unable to make a connection with google analytics server",
					GoogleAnalyticsErrorType.CONNECTION_EXCEPTION);
		}

		GoogleCredential credential = null;
		try {
			credential = GoogleCredential.fromStream(path).createScoped(AnalyticsScopes.all());
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsConnection::generateReport()", e);
			throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file",
					GoogleAnalyticsErrorType.CONNECTION_EXCEPTION);
		}

		// Construct the Analytics service object.
		analytics = new Analytics.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(applicationName)
				.build();
	}

	public void disconnect() {
		// do something to invalidate this connection!
	}

	public Analytics getAnalytics() {
		return analytics;
	}
}
