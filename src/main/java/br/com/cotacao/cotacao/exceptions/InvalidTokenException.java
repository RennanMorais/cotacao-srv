package br.com.cotacao.cotacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException {
	
	public InvalidTokenException(String mensagem) {
		super(mensagem);
	}
	
}
