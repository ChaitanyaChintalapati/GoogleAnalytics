package org.mule.modules.googleanalytics.internal.exception;

import static org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError.EXECUTION;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.Set;

public class ExecutionErrorTypeProvider extends BaseErrorTypeProvider {

	@Override
	public void addErrors(Set<ErrorTypeDefinition> errors) {
		errors.add(EXECUTION);
	}
}