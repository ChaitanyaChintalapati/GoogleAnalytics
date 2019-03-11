  package org.mule.modules.googleanalytics.api.params;

import org.mule.modules.googleanalytics.api.domain.FilterKey;
import org.mule.modules.googleanalytics.api.domain.FilterOperation;
import org.mule.modules.googleanalytics.api.domain.FilterOperator;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class FilterParameterType {

	public FilterParameterType() {
	}

	public FilterParameterType(FilterKey key, FilterOperator operator, String value, FilterOperation operation) {
		this.key = key;
		this.operator = operator;
		this.value = value;
		this.operation = operation;
	}

	@Parameter
	private FilterKey key;

	@Parameter
	private FilterOperator operator;

	@Parameter
	private String value;

	@Parameter
	@Optional
	private FilterOperation operation;

	public FilterKey getKey() {
		return key;
	}

	public void setKey(FilterKey key) {
		this.key = key;
	}

	public FilterOperator getOperator() {
		return operator;
	}

	public void setOperator(FilterOperator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FilterOperation getOperation() {
		return operation;
	}

	public void setOperation(FilterOperation operation) {
		this.operation = operation;
	}

}
