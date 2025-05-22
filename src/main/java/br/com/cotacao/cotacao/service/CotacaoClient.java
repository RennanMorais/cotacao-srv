package br.com.cotacao.cotacao.service;

import br.com.cotacao.cotacao.domain.CotacaoRequest;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class CotacaoClient {

    private HttpClient client;

    private HttpRequest httpReq;

    public String chamadaHttpGet(String uri) throws IOException, InterruptedException, KeyManagementException, NoSuchAlgorithmException {

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        this.client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();

        httpReq = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = client.send(httpReq, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
