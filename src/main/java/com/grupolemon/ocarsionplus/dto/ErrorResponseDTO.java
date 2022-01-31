package com.grupolemon.ocarsionplus.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name = "error")
public class ErrorResponseDTO {
	
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

	public ErrorResponseDTO(int statusCode, Date timestamp, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

}