package org.mule.modules.googleanalytics.api.domain;

public enum SamplingLevel {

	DEFAULT("DEFAULT"), FASTER("FASTER"), HIGHER_PRECISION("HIGHER_PRECISION");

	private String samplingLevel;

	public String getSamplingLevel() {
		return samplingLevel;
	}

	public void setSamplingLevel(String samplingLevel) {
		this.samplingLevel = samplingLevel;
	}

	private SamplingLevel(String samplingLevel) {
		this.samplingLevel = samplingLevel;
	}

}
