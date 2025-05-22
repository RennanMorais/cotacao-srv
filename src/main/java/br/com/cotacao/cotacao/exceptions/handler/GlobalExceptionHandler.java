package br.com.cotacao.cotacao.exceptions.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;
import javax.security.sasl.AuthenticationException;

import br.com.cotacao.cotacao.exceptions.ErroFluxoException;
import br.com.cotacao.cotacao.exceptions.InvalidLoginException;
import br.com.cotacao.cotacao.util.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
	public ResponseEntity<Object> validationException(Exception e) {
		br.com.cotacao.cotacao.exceptions.handler.ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Object> anyExceptions(Exception e) {
		br.com.cotacao.cotacao.exceptions.handler.ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(LoginException.class)
	public ResponseEntity<Object> handleLoginException(LoginException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorDto.CodeErrorDto> handleAuthenticationException(Exception ex) {
		ErrorDto.CodeErrorDto erro = new ErrorDto.CodeErrorDto(HttpStatus.UNAUTHORIZED.toString(), "Token Inválido");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<Object> handleInvalidLoginException(InvalidLoginException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDto.CodeErrorDto> erros =  new ArrayList<>();
        if (ex.getBindingResult().getFieldErrors().isEmpty()) {
			erros.addAll(ex.getBindingResult().getAllErrors().stream()
					.map(error -> new ErrorDto.CodeErrorDto(
							String.valueOf(HttpStatus.BAD_REQUEST.value()), 
							error.getDefaultMessage()))
					.collect(Collectors.toList()));
		}
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			ErrorDto.CodeErrorDto codeError = new ErrorDto.CodeErrorDto(
					String.valueOf(HttpStatus.BAD_REQUEST.value()), 
					error.getField() + ": " + error.getDefaultMessage());
			erros.add(codeError);
		}

		return ResponseEntity.badRequest().body(new ErrorDto(HttpStatus.BAD_REQUEST, erros));
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ErroFluxoException.class)
	public ResponseEntity<Object> handleErroFluxoException(ErroFluxoException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}
