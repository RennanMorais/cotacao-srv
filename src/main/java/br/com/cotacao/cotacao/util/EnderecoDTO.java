package br.com.cotacao.cotacao.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private Long idEstado;
	private Long idCidade;
	
}
