
/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
