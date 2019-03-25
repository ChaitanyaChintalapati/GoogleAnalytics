package org.mule.modules.googleanalytics.internal.exception;

import static java.util.Optional.ofNullable;
import static org.mule.runtime.extension.api.error.MuleErrors.CONNECTIVITY;

import java.util.Optional;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

/**
 * mule-alltogether
 *
 * @author Chaitanya Chintalapati (https://github.com/ChaitanyaChintalapati)
 */
public enum GoogleAnalyticsError implements ErrorTypeDefinition<GoogleAnalyticsError> {

	
	EXECUTION,
	
	JSON_PARSER_EXCEPTION(EXECUTION),
	OPERATION_NOT_APPLIED(EXECUTION),
	INVALID_QUERY(EXECUTION),
	NO_HOST_AVAILABLE(EXECUTION),
	OPERATION_TIMED_OUT(EXECUTION),
	OPERATION_FAILED(EXECUTION),
	SYNTAX_ERROR(EXECUTION),
	SERVERE_RROR(EXECUTION),
	QUERY_ERROR(EXECUTION),
	ACCOUNT_NOT_FOUND(EXECUTION),
	NO_PROFILES_FOUND(EXECUTION),
	READ_FAILURE(EXECUTION),
	READ_TIMEOUT(EXECUTION),
    TRIGGER_EXPIRED(EXECUTION),
    NOT_AUTHED(CONNECTIVITY),
    CONNECTION_EXCEPTION(CONNECTIVITY),
    INCORRECT_CREDENTIALS(CONNECTIVITY),
    INVALID_AUTH(CONNECTIVITY),
    NO_PERMISSION(CONNECTIVITY),
    ACCOUNT_INACTIVE(CONNECTIVITY);


	 
	
    private ErrorTypeDefinition parent;

    GoogleAnalyticsError(ErrorTypeDefinition parent) {
        this.parent = parent;
    }

    GoogleAnalyticsError() {

    }

    @Override
    public Optional<ErrorTypeDefinition<? extends Enum<?>>> getParent() {
        return ofNullable(parent);
    }
	
	
	 
	
}
