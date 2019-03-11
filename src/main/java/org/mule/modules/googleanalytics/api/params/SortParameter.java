package org.mule.modules.googleanalytics.api.params;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;

import java.util.LinkedList;
import java.util.List;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.ExclusiveOptionals;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@ExclusiveOptionals
public class SortParameter {

	
	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	@ParameterDsl(allowReferences = false)
	private List<SortParameterType> sortparms = new LinkedList<>();

	public List<SortParameterType> getSortparms() {
		return sortparms;
	}

	public void setSortparms(List<SortParameterType> sortparms) {
		this.sortparms = sortparms;
	}
}
