package org.mule.modules.googleanalytics.api.domain;

public enum FilterOperation {
	OR(","), AND(";");

	private String operation;

	private FilterOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
