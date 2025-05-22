package br.com.cotacao.cotacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidLoginException extends RuntimeException {
	
	public InvalidLoginException(String mensagem) {
		super(mensagem);
	}
	
}
