package org.mule.modules.googleanalytics.api.domain;

public enum SegmentOperator {
	
	EqualTo("=="), NotEqual("!="), GreaterThan(">"), LessThan("<"), GreaterThanEqualTo(">="), LessThanOrEqualTo("<="), Between("<>"),InList("[]"),
	ContainsSubstring("=@"), DoesNotContainSubstring("!@"), ContainsAMatchForRegularEXpression("=~"), DoesNotMatchForRegularEXpression("!~");

	
	private String segmentOperator;

	public String getSegmentOperator() {
		return segmentOperator;
	}

	public void setSegmentOperator(String segmentOperator) {
		this.segmentOperator = segmentOperator;
	}

	private SegmentOperator(String segmentOperator) {
		this.segmentOperator = segmentOperator;
	}
	
	

}
