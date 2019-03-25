package org.mule.modules.googleanalytics.internal.connection;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.mule.modules.googleanalytics.internal.model.GoogleAnalyticsOAuth2Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;

public class GoogleAnalyticsConnection {
	
	private static final Logger log = LoggerFactory.getLogger(GoogleAnalyticsConnection.class);
	
	/**
	   * Initializes an Analytics service object.
	   *
	   * @return An authorized Analytics service object.
	   * 
	   */
	public Analytics initializeGoogleAnalytic(String clientId, String clientSecret, String domain, int port, String applicationName)  throws GoogleAnalyticsException  {
		
		final File DATA_STORE_DIR = new File("google_analytics_two");
		FileDataStoreFactory DATA_STORE_FACTORY = null;
		try {
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsConnection::initializeGoogleAnalytic()", e);
			throw new GoogleAnalyticsException("Unble to creatre data store factory", GoogleAnalyticsError.CONNECTION_EXCEPTION);
		}
		
		final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
		HttpTransport HTTP_TRANSPORT = null;
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e) {
			log.error("Error occured in GoogleAnalyticsConnection::initializeGoogleAnalytic()", e);
			throw new GoogleAnalyticsException("Unable to create google analytics transport", GoogleAnalyticsError.CONNECTION_EXCEPTION);
		}
	
		// set up authorization code flow
	    AuthorizationCodeFlow authorizationCodeFlow = null;
		try {
			authorizationCodeFlow = new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(), HTTP_TRANSPORT, JSON_FACTORY, new GenericUrl(GoogleAnalyticsOAuth2Config.TOKEN_SERVER_URL), new ClientParametersAuthentication(clientId, clientSecret), clientId, GoogleAnalyticsOAuth2Config.AUTHORIZATION_SERVER_URL).setScopes(AnalyticsScopes.all()).setDataStoreFactory(DATA_STORE_FACTORY).build();
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsConnection::initializeGoogleAnalytic()", e);
			throw new GoogleAnalyticsException("Innvalid authorization", GoogleAnalyticsError.INVALID_AUTH);
		}
		
	    // authorize
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost(domain).setPort(port).build();
	    
		Credential credential = null;
		try {
			credential = new AuthorizationCodeInstalledApp(authorizationCodeFlow, receiver).authorize("user");
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsConnection::initializeGoogleAnalytic()", e);
			throw new GoogleAnalyticsException("User is not authorized", GoogleAnalyticsError.NOT_AUTHED);
		}
		
		// Construct the Analytics service object.
		return new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(applicationName).build();
		
	
	}
}

