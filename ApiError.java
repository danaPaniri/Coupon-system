package coupons.coupon.system.entities;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	private HttpStatus status;
	private String messages;
	
	
	public ApiError(HttpStatus status, String messages) {
		super();
		this.status = status;
		this.messages = messages;
	}




	public HttpStatus getStatus() {
		return status;
	}




	public void setStatus(HttpStatus status) {
		this.status = status;
	}




	public String getMessages() {
		return messages;
	}




	public void setMessages(String messages) {
		this.messages = messages;
	}
	

}
