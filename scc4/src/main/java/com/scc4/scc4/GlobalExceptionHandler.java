package com.scc4.scc4;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Map<String,String>> globleExcpetionHandler(Exception ex, WebRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("time", new Date().toString());
		map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		map.put("message", ex.getMessage());
		map.put("details", request.getDescription(false));

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
