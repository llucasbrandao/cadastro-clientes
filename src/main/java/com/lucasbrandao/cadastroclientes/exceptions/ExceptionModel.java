package com.lucasbrandao.cadastroclientes.exceptions;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private Date timeStamp;
	private String url;
	private HttpStatus httpStatus;
	
	public ExceptionModel() {}
	
	public ExceptionModel(String message) {
		this.message = message;
	}

	public ExceptionModel(String message, Date timeStamp, String url, HttpStatus httpStatus) {
		this.message = message;
		this.timeStamp = timeStamp;
		this.url = url;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}