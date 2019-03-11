package org.mule.modules.googleanalytics.api.domain;

public enum FilterOperator {

	Equals("=="), DoesNotEqual("!="), GreaterThan(">"), LessThan("<"), GreaterThanEqualTo(">="), LessThanOrEqualTo("<="), 
	ContainsSubstring("=@"), DoesNotContainSubstring("!@"), ContainsAMatchForRegularEXpression("=~"), DoesNotMatchForRegularEXpression("!~");

	private String FilterOperator;

	public String getFilterOperator() {
		return FilterOperator;
	}

	public void setFilterOperator(String filterOperator) {
		FilterOperator = filterOperator;
	}

	private FilterOperator(String filterOperator) {
		FilterOperator = filterOperator;
	}

}
