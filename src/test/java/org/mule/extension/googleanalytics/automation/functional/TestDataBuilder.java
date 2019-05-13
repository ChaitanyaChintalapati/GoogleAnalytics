/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.extension.googleanalytics.automation.functional;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataBuilder {

	protected static Properties getAutomationCredentialsProperties() throws IOException {
		Properties properties = new Properties();
		String automationFile = format("%s/src/test/resources/%s", getProperty("user.dir"),
				ofNullable(getProperty("automation-credentials.properties"))
						.orElse("automation-credentials.properties"));
		try (InputStream inputStream = new FileInputStream(automationFile)) {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new FileNotFoundException(format("property file '%s' not found in the classpath", automationFile));
		}
		return properties;
	}

}
