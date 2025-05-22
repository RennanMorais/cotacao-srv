package br.com.cotacao.cotacao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DadosPessoaisException extends RuntimeException {
	
	public DadosPessoaisException(String mensagem) {
		super(mensagem);
	}
 	
}
