/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.googleanalytics.api.domain;

public enum Output {

	JSON("json"), DATATABLE("dataTable");

	private String output;

	private Output(){};
	
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
