package org.mule.modules.googleanalytics.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

import static org.mule.runtime.api.meta.Category.COMMUNITY;

import org.mule.modules.googleanalytics.internal.configuration.GoogleAnalyticsConfiguration;
import org.mule.modules.googleanalytics.internal.connection.OAuth2ConnectionProvider;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.runtime.extension.api.annotation.license.RequiresEnterpriseLicense;

/**
 * This is the main class of an extension, is the entry point from which
 * configurations, connection providers, operations and sources are going to be
 * declared.
 */
@Xml(prefix = "google-analytics")
@Extension(name = "Google Analytics", vendor = "Ksquare", category = COMMUNITY)
@ConnectionProviders({ OAuth2ConnectionProvider.class })
@Configurations(GoogleAnalyticsConfiguration.class)
@ErrorTypes(GoogleAnalyticsError.class)
@RequiresEnterpriseLicense(allowEvaluationLicense = true)
public class GoogleAnalyticsExtension {

}
