/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.googleanalytics.internal.exception;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import static org.mule.modules.googleanalytics.internal.error.GoogleAnalyticsErrorType.EXECUTION;

import java.util.Set;

public class ExecutionErrorTypeProvider extends BaseErrorTypeProvider {

	@Override
	public void addErrors(Set<ErrorTypeDefinition> errors) {
		errors.add(EXECUTION);
	}
}