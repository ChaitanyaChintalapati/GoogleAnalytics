package org.mule.modules.googleanalytics.api.params;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;

import org.mule.modules.googleanalytics.api.domain.MetrixParamValue;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.ExclusiveOptionals;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@ExclusiveOptionals
public class MetricsParameter {

	@Parameter
	@Optional
	@Expression(NOT_SUPPORTED)
	private MetrixParamValue value;

	public MetrixParamValue getValue() {
		return value;
	}

	public void setValue(MetrixParamValue value) {
		this.value = value;
	}

}
