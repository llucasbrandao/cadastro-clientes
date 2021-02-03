package com.lucasbrandao.cadastroclientes.exceptions;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	private String url;
	private Date timeStamp;
	private HttpStatus httpStatus;
	
	public ExceptionModel() {}
	
	public ExceptionModel(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public ExceptionModel(String mensagem, String url, Date timeStamp, HttpStatus httpStatus) {
		super();
		this.mensagem = mensagem;
		this.url = url;
		this.timeStamp = timeStamp;
		this.httpStatus = httpStatus;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}