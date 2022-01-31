package com.grupolemon.ocarsionplus.rest.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.grupolemon.ocarsionplus.dto.ErrorResponseDTO;
import com.grupolemon.ocarsionplus.service.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> entityNotFoundException(EntityNotFoundException ex) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDTO> illegalArgumentException(IllegalArgumentException ex) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage(),
				null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), new Date(),
				ex.getFieldError().getDefaultMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorResponseDTO message = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
				ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}