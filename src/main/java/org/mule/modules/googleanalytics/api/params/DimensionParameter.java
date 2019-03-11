package org.mule.modules.googleanalytics.api.params;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;

import org.mule.modules.googleanalytics.api.domain.DimensionParamValue;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class DimensionParameter {

	@Parameter
	@Optional
	@Expression(NOT_SUPPORTED)
	private DimensionParamValue value;

	public DimensionParamValue getValue() {
		return value;
	}

	public void setValue(DimensionParamValue value) {
		this.value = value;
	}

}
