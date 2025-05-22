package br.com.cotacao.cotacao.exceptions;

@SuppressWarnings("serial")
public class ErroFluxoException extends RuntimeException {

	public ErroFluxoException(String mensagem) {
		super(mensagem);
	}
	
}
