package com.employee.record.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice

public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		logger.error("SQLException Occured:: URL=" + request.getRequestURL());
		return "database_error";
	}

	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleBadInputException(Exception e) {
		logger.error("IOException handler executed");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<String> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		logger.error(ex.getCause().getMessage());

		return new ResponseEntity<>(bodyOfResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceAccessException.class)
	protected ResponseEntity<String> handleBusinessException(ResponseError responseError) {
		logger.error("IOException handler executed");
		// returning 500 error code
		return new ResponseEntity<>(responseError.getBody(), responseError.getStatusCode());
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "IOException occured")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseError> handleGeneralException(Exception e) {
		logger.error(e.getMessage(), e.getClass());
		return new ResponseEntity<ResponseError>(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
