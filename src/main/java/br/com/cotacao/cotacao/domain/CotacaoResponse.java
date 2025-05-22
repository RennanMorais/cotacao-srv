package br.com.cotacao.cotacao.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CotacaoResponse {

    private String moedaEnviada;
    private String moedaConversao;
    private String cotacao;

}
