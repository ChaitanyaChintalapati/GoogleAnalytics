/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.googleanalytics.internal.config;

import org.mule.modules.googleanalytics.internal.operation.GoogleAnalyticsOperations;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Operations;

@Configuration(name = "config")
@Operations({GoogleAnalyticsOperations.class})
public class GoogleAnalyticsConfiguration  {

}
