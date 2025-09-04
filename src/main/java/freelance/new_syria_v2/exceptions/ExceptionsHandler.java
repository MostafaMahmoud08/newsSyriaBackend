package freelance.new_syria_v2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import freelance.new_syria_v2.exceptions.response.ExceptionResponse;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse>responseEntity(NotFoundException exception){
		ExceptionResponse response=new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse>responseEntity(Exception exception){
		ExceptionResponse response=new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
