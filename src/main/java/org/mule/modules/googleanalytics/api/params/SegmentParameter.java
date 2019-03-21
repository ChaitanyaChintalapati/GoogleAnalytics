/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.googleanalytics.api.params;

import java.util.LinkedList;
import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SegmentParameter {

	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	@ParameterDsl(allowReferences = false)

	private List<SegmentParameterType> segmentparams = new LinkedList<>();

	public List<SegmentParameterType> getSegmentparams() {
		return segmentparams;
	}

	public void setSegmentparams(List<SegmentParameterType> segmentparams) {
		this.segmentparams = segmentparams;
	}

}
