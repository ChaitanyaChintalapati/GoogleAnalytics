package org.mule.modules.googleanalytics.internal.exception;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import org.mule.runtime.extension.api.exception.ModuleException;

public class GoogleAnalyticsException extends ModuleException{
	
    public <T extends Enum<T>> GoogleAnalyticsException(String message, ErrorTypeDefinition<T> errorTypeDefinition) {
        super(message, errorTypeDefinition);
    }

	

	
}
