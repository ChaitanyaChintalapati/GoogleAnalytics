package org.mule.modules.googleanalytics.internal.connection;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.mule.modules.googleanalytics.internal.model.GoogleAnalyticsOAuth2Config;

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
	
	
	
	public Analytics initializeGoogleAnalytic(String clientId, String clientSecret, String domain, int port, String applicationName) throws GeneralSecurityException, IOException {
		
		final File DATA_STORE_DIR = new File("google_analytics_one");
		final FileDataStoreFactory DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		
		final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
		final HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		// set up authorization code flow
	    AuthorizationCodeFlow authorizationCodeFlow = new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(), HTTP_TRANSPORT, JSON_FACTORY, new GenericUrl(GoogleAnalyticsOAuth2Config.TOKEN_SERVER_URL), new ClientParametersAuthentication(clientId, clientSecret), clientId, GoogleAnalyticsOAuth2Config.AUTHORIZATION_SERVER_URL)
	    		.setScopes(AnalyticsScopes.all())
	    		.setDataStoreFactory(DATA_STORE_FACTORY).build();
		
	    // authorize
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost(domain).setPort(port).build();
	    
		Credential credential = new AuthorizationCodeInstalledApp(authorizationCodeFlow, receiver).authorize("user");
		return new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(applicationName).build();
		
	}

}
