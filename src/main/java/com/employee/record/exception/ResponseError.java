package com.employee.record.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;

public class ResponseError extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus statusCode;

    private String body;

    public ResponseError(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    public ResponseError(org.springframework.http.HttpStatus httpStatus, String body, String msg) {
        super(msg);
        this.statusCode = httpStatus;
        this.body = body;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
