package pl.kaczmarczyk.cms.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ApiFieldError> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> new ApiFieldError(fieldError.getField(), fieldError.getDefaultMessage()))
				.toList();

		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errors));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}