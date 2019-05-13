package org.mule.modules.googleanalytics.internal.config;

import org.mule.modules.googleanalytics.internal.connection.provider.GoogleAnalyticsConnectionProvider;
import org.mule.modules.googleanalytics.internal.operation.GoogleAnalyticsOperations;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(GoogleAnalyticsOperations.class)
@ConnectionProviders(GoogleAnalyticsConnectionProvider.class)
public class GoogleAnalyticsConfiguration {

  
  }

