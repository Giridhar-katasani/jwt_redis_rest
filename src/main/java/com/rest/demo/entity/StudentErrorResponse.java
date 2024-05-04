package com.rest.demo.entity;

public class StudentErrorResponse {

	@Override
	public String toString() {
		return "StudentErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

	private int status;
	private String message;
	private long timeStamp;

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

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
