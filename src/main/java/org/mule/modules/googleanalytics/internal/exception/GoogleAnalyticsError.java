package org.mule.modules.googleanalytics.internal.exception;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import static java.util.Optional.ofNullable;
import static org.mule.runtime.extension.api.error.MuleErrors.ANY;
import org.omg.CORBA.UNKNOWN;

import java.util.Optional;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import org.mule.runtime.extension.api.error.MuleErrors;

public enum GoogleAnalyticsError implements ErrorTypeDefinition<GoogleAnalyticsError> {

	
	 GOOGLE_ANALYTICS_EXECUTION(ANY),
     AUTHENTICATION(GOOGLE_ANALYTICS_EXECUTION),
    CONNECTION(GOOGLE_ANALYTICS_EXECUTION),
    INVALID_QUERY(GOOGLE_ANALYTICS_EXECUTION),
    NO_HOST_AVAILABLE(GOOGLE_ANALYTICS_EXECUTION),
    OPERATION_TIMED_OUT(GOOGLE_ANALYTICS_EXECUTION),
    OPERATION_FAILED(GOOGLE_ANALYTICS_EXECUTION),
    READ_FAILURE(GOOGLE_ANALYTICS_EXECUTION),
    READ_TIMEOUT(GOOGLE_ANALYTICS_EXECUTION),
    SERVERE_RROR(GOOGLE_ANALYTICS_EXECUTION),
    SYNTAX_ERROR(GOOGLE_ANALYTICS_EXECUTION),
    UNAUTHORIZED(GOOGLE_ANALYTICS_EXECUTION),
    UNAVAILABLE(GOOGLE_ANALYTICS_EXECUTION),

    GOOGLE_ANALYTICS_EXCEPTION(ANY),
    QUERY_ERROR(GOOGLE_ANALYTICS_EXCEPTION),
    OPERATION_NOT_APPLIED(GOOGLE_ANALYTICS_EXCEPTION),

    CONNECTIVITY(MuleErrors.CONNECTIVITY),
    UNKNOWN(CONNECTIVITY);

    private ErrorTypeDefinition<?> parentErrorType;

    GoogleAnalyticsError(final ErrorTypeDefinition<?> parentErrorType) {
        this.parentErrorType = parentErrorType;
    }

    @Override
    public Optional<ErrorTypeDefinition<? extends Enum<?>>> getParent() {
        return ofNullable(parentErrorType);
    }
	
}
