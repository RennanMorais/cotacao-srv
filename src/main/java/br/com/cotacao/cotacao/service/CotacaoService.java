package br.com.cotacao.cotacao.service;

import br.com.cotacao.cotacao.domain.CotacaoDados;
import br.com.cotacao.cotacao.domain.CotacaoRequest;
import br.com.cotacao.cotacao.domain.CotacaoResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;

@Service
public class CotacaoService {

    private CotacaoClient client;

    public CotacaoService(CotacaoClient client) {
        this.client = client;
    }

    public CotacaoResponse buscarCotacaoAtual(CotacaoRequest request) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        String urlapi = "https://economia.awesomeapi.com.br/last/";
        String moedasUtl = request.getMoedaConversao().concat("-").concat(request.getMoeda());
        String urlFinal = urlapi+moedasUtl;

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, CotacaoDados>>() {}.getType();

        String respostaApi = client.chamadaHttpGet(urlFinal);

        Map<String, CotacaoDados> cotacaoMap = gson.fromJson(respostaApi, type);

        CotacaoDados dados = null;

        for (Map.Entry<String, CotacaoDados> entry : cotacaoMap.entrySet()) {
            dados = entry.getValue();
        }

        return CotacaoResponse.builder()
                .moedaEnviada(dados.getCode())
                .moedaConversao(dados.getCodein())
                .cotacao(dados.getBid())
                .build();
    }

}
