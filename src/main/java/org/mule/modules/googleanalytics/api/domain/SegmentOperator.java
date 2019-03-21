
/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

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
