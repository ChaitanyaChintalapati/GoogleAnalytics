
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
                ofNullable(getProperty("automation-credentials.properties")).orElse("automation-credentials.properties"));
        try (InputStream inputStream = new FileInputStream(automationFile)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new FileNotFoundException(format("property file '%s' not found in the classpath", automationFile));
        }
        return properties;
    }

}
