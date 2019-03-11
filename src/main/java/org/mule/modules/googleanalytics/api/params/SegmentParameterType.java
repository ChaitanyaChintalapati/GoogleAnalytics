package org.mule.modules.googleanalytics.api.params;

import org.mule.modules.googleanalytics.api.domain.FilterKey;
import org.mule.modules.googleanalytics.api.domain.FilterOperator;
import org.mule.modules.googleanalytics.api.domain.SegmentKey;
import org.mule.modules.googleanalytics.api.domain.SegmentOperator;
import org.mule.modules.googleanalytics.api.domain.SegmentType;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SegmentParameterType {

	public SegmentParameterType() {

	}

	public SegmentParameterType(SegmentKey segmentKey, SegmentType segmentType, FilterKey segmentFilter,
			SegmentOperator segmentOpertor, String segmentValue) {
		super();
		this.segmentKey = segmentKey;
		this.segmentType = segmentType;
		this.segmentFilter = segmentFilter;
		this.segmentOpertor = segmentOpertor;
		this.segmentValue = segmentValue;
	}

	public SegmentKey getSegmentKey() {
		return segmentKey;
	}

	public void setSegmentKey(SegmentKey segmentKey) {
		this.segmentKey = segmentKey;
	}

	public SegmentType getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(SegmentType segmentType) {
		this.segmentType = segmentType;
	}

	public FilterKey getSegmentFilter() {
		return segmentFilter;
	}

	public void setSegmentFilter(FilterKey segmentFilter) {
		this.segmentFilter = segmentFilter;
	}

	

	public SegmentOperator getSegmentOpertor() {
		return segmentOpertor;
	}

	public void setSegmentOpertor(SegmentOperator segmentOpertor) {
		this.segmentOpertor = segmentOpertor;
	}

	public String getSegmentValue() {
		return segmentValue;
	}

	public void setSegmentValue(String segmentValue) {
		this.segmentValue = segmentValue;
	}

	@Parameter
	private SegmentKey segmentKey;

	@Parameter
	private SegmentType segmentType;

	@Parameter
	private FilterKey segmentFilter;

	@Parameter
	private SegmentOperator segmentOpertor;

	@Parameter
	private String segmentValue;

}
