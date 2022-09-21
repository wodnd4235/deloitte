package com.deloitte.solution.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class BadRequestHandler {
	@ExceptionHandler({MaxUploadSizeExceededException.class})
	public ResponseEntity<? extends BasicResponse> uploadException(MaxUploadSizeExceededException exc, 
		      HttpServletRequest request,
		      BadRequestHandler response) {
		
		return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(new ErrorResponse(String.valueOf(HttpStatus.PAYLOAD_TOO_LARGE.value()), "파일 사이즈를 초과하였습니다. (50MB 이하의 파일을 선택해주세요.)"));
	}
}
