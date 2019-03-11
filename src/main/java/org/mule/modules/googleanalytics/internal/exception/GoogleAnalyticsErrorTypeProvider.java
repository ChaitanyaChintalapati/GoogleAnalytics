package org.mule.modules.googleanalytics.internal.exception;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;


public class GoogleAnalyticsErrorTypeProvider implements ErrorTypeProvider {
	
	@Override
    public Set<ErrorTypeDefinition> getErrorTypes() {
        return Stream.of(GoogleAnalyticsError.values()).collect(toSet());
    }
	

}
