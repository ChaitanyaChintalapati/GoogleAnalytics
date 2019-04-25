/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

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
