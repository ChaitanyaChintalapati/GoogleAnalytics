package org.mule.modules.googleanalytics.internal.exception;

import static org.mule.runtime.extension.api.error.MuleErrors.CONNECTIVITY;

import java.util.HashSet;
import java.util.Set;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public abstract class BaseErrorTypeProvider implements ErrorTypeProvider {

	public abstract void addErrors(Set<ErrorTypeDefinition> errors);

	@Override
	public final Set<ErrorTypeDefinition> getErrorTypes() {
		HashSet<ErrorTypeDefinition> errors = new HashSet<>();
		errors.add(CONNECTIVITY);
		addErrors(errors);
		return errors;
	}

}
