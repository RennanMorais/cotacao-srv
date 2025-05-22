package br.com.cotacao.cotacao.resource;

import br.com.cotacao.cotacao.domain.CotacaoRequest;
import br.com.cotacao.cotacao.domain.CotacaoResponse;
import br.com.cotacao.cotacao.service.CotacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@RestController()
@RequestMapping(value = "/api")
public class CotacaoResource {

    private final CotacaoService service;

    public CotacaoResource(CotacaoService service) {
        this.service = service;
    }

    @PostMapping("/cotacao")
    public ResponseEntity<CotacaoResponse> cotacaoAtual(@RequestBody CotacaoRequest request) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        return ResponseEntity.ok(this.service.buscarCotacaoAtual(request));
    }

}
