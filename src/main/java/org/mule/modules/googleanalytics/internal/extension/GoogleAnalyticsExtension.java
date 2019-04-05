package org.mule.modules.googleanalytics.internal.extension;

import static org.mule.runtime.api.meta.Category.COMMUNITY;

import org.mule.modules.googleanalytics.internal.config.GoogleAnalyticsConfiguration;
import org.mule.modules.googleanalytics.internal.connection.GoogleAnalyticsConnectionProvider;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.modules.googleanalytics.internal.operation.GoogleAnalyticsOperations;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
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
@ConnectionProviders(GoogleAnalyticsConnectionProvider.class)
@Operations(GoogleAnalyticsOperations.class)
@ErrorTypes(GoogleAnalyticsError.class)
@RequiresEnterpriseLicense(allowEvaluationLicense = true)
public class GoogleAnalyticsExtension {

}
