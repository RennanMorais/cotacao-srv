package br.com.cotacao.cotacao.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpRequest;

@Builder
@Getter
@Setter
public class CotacaoRequest {

    private String moeda;
    private String moedaConversao;

}
