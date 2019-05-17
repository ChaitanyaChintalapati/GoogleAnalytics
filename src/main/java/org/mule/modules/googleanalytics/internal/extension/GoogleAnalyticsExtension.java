
/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.googleanalytics.internal.extension;

import static org.mule.runtime.api.meta.Category.CERTIFIED;

import org.mule.modules.googleanalytics.internal.config.GoogleAnalyticsConfiguration;
import org.mule.modules.googleanalytics.internal.error.GoogleAnalyticsErrorType;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.runtime.extension.api.annotation.license.RequiresEnterpriseLicense;

/**
 * This is the main class of an extension, is the entry point from which
 * configurations, connection providers, operations and sources are going to be
 * declared.
 */
@Xml(prefix = "google-analytics")
@Extension(name = "Google Analytics", vendor = "Ksquare", category = CERTIFIED)
@ErrorTypes(GoogleAnalyticsErrorType.class)
@Configurations(GoogleAnalyticsConfiguration.class)
@RequiresEnterpriseLicense(allowEvaluationLicense = true)
public class GoogleAnalyticsExtension {

}

