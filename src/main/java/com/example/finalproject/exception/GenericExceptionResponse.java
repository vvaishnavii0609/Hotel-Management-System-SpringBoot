package com.example.finalproject.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class GenericExceptionResponse {

	
	LocalDateTime timestamp;
	int status;
	String message;
	
	
	public GenericExceptionResponse(LocalDateTime timestamp, int status, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "GenericExceptionResponse [timestamp=" + timestamp + ", status=" + status + ", message=" + message + "]";
	}
}
	