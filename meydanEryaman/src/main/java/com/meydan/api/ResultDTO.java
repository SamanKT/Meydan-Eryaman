package com.meydan.api;

import javax.validation.constraints.NotNull;

public class ResultDTO {

	@NotNull(message = "bu alan bos olamaz")
	private Long telResult;
	
	private String nameResult;
	public Long getTelResult() {
		return telResult;
	}
	public void setTelResult(Long telResult) {
		this.telResult = telResult;
	}
	public String getNameResult() {
		return nameResult;
	}
	public void setNameResult(String nameResult) {
		this.nameResult = nameResult;
	}
	
}
