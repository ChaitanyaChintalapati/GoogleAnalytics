
/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.googleanalytics.api.domain;

public enum SortOrder {

	
	ASCENDING("+"),
	DESCENDING("-");
	
	private String sortOrder;

	private SortOrder(){};

	private SortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
