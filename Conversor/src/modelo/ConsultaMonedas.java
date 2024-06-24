package modelo;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMonedas {

    public Moneda buscaMoneda() {
        String url = "https://v6.exchangerate-api.com/v6/f47de46c90f7f96a44a3adcc/latest/USD";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos de la API de tasas de cambio", e);
        }
    }
}
