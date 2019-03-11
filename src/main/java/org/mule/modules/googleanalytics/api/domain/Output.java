package org.mule.modules.googleanalytics.api.domain;

public enum Output {

	JSON("json"), DATATABLE("dataTable");

	private String output;

	private Output(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
