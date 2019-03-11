package org.mule.modules.googleanalytics.api.domain;

public enum SegmentKey {

	Users("users::"), Sessions("sessions::");
	private String segmentKey;

	public String getSegmentKey() {
		return segmentKey;
	}

	public void setSegmentKey(String segmentKey) {
		this.segmentKey = segmentKey;
	}

	private SegmentKey(String segmentKey) {
		this.segmentKey = segmentKey;
	}

}
