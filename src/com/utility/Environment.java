package com.utility;

import org.codehaus.jackson.annotate.JsonProperty;

public class Environment {

	private String environmentType;


	public Environment() {
		environmentType = "prod";
	}

	public String getEnvironmentType() {
		return environmentType;
	}

	@JsonProperty("EnvironmentType")
	public void setEnvironmentType(String environmentType) {
		this.environmentType = environmentType;
	}

}
