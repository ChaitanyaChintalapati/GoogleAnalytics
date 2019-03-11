package org.mule.modules.googleanalytics.api.domain;

public enum SegmentType {

	Condition("condition::"), Sequence("sequence::");

	private String segmentType;

	public String getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(String segmentType) {
		this.segmentType = segmentType;
	}

	private SegmentType(String segmentType) {
		this.segmentType = segmentType;
	}

}
