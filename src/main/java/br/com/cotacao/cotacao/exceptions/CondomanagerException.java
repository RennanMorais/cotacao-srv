package br.com.cotacao.cotacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CondomanagerException extends RuntimeException {
	
	public CondomanagerException(String mensagem) {
		super(mensagem);
	}
	
}
