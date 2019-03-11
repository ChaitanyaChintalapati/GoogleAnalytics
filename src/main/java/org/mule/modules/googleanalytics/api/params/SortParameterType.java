package org.mule.modules.googleanalytics.api.params;

import javax.swing.SortOrder;

import org.mule.modules.googleanalytics.api.domain.FilterKey;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SortParameterType {
	
	
	
	@Parameter
	private FilterKey sortparamValue;
	
	@Parameter
	private SortOrder sortOrder;

	public FilterKey getSortparamValue() {
		return sortparamValue;
	}

	public void setSortparamValue(FilterKey sortparamValue) {
		this.sortparamValue = sortparamValue;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	
	public SortParameterType(){
		
	}
	
	public SortParameterType(FilterKey sortparamValue, SortOrder sortOrder) {
		super();
		this.sortparamValue = sortparamValue;
		this.sortOrder = sortOrder;
	}
	
	

}
