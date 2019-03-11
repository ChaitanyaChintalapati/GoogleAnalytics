package org.mule.modules.googleanalytics.api.params;

import java.util.LinkedList;
import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class FilterParameter {
	
	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	@ParameterDsl(allowReferences = false)
	private List<FilterParameterType> filterParams = new LinkedList<>();

	
	
	public List<FilterParameterType> getFilterParams() {
		return filterParams;
	}

}
