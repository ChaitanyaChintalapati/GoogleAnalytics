package org.mule.modules.googleanalytics.api.domain;

public enum SortOrder {

	
	ASCENDING("+"),
	DESCENDING("-");
	
	private String sortOrder;

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	private SortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
