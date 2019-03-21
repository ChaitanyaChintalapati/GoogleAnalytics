
/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
