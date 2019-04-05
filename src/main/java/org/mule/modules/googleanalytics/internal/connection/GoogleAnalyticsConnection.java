package org.mule.modules.googleanalytics.internal.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.mule.modules.googleanalytics.internal.operation.GoogleAnalyticsOperations;
import org.mule.runtime.extension.api.annotation.Operations;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;

public class GoogleAnalyticsConnection {
	
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

		if(path == null)
			throw new GoogleAnalyticsException("Unable to load provided json file path : " + jsonFilePath, GoogleAnalyticsError.CONNECTION_EXCEPTION);
		
		JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
		HttpTransport httpTransport;
		
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e) {
			throw new GoogleAnalyticsException("Unable to make a connection with google analytics server", GoogleAnalyticsError.CONNECTION_EXCEPTION);
		}

		GoogleCredential credential = null;
		try {
			credential = GoogleCredential.fromStream(path).createScoped(AnalyticsScopes.all());
		} catch (IOException e) {
			throw new GoogleAnalyticsException("Unable to get google access token. Please check you json file", GoogleAnalyticsError.CONNECTION_EXCEPTION);
		}

		// Construct the Analytics service object.
		analytics = new Analytics.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(applicationName).build();
	}

	public void disconnect() {
		// do something to invalidate this connection!
	}

	public Analytics getAnalytics() {
		return analytics;
	}
	
}
