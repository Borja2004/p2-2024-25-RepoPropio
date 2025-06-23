package org.DIS.practica2.servicios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.DIS.practica2.modelos.Turismo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class FrontService {
    @Value("${backend.url}")
    private String URL_API;

    public ArrayList<Turismo> getTurismos() {
        String url = String.format("%s/db", URL_API);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Turismo>>() {}.getType();
            return gson.fromJson(response.body(), listType);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Turismo getTurismo(String id) throws IOException, InterruptedException, URISyntaxException {
        String url = String.format("%s/db/%s", URL_API, id);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        return gson.fromJson(response.body(), Turismo.class);
    }

    public Turismo editarTurismo(String id, Turismo turismo) {
        String url = String.format("%s/db/%s", URL_API, id);
        try {
            HttpClient client = HttpClient.newHttpClient();
            Gson gson = new Gson();
            String json = gson.toJson(turismo);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Turismo.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Turismo crearTurismo(Turismo turismo) {
        String url = String.format("%s/db", URL_API);
        try {
            HttpClient client = HttpClient.newHttpClient();
            Gson gson = new Gson();
            String json = gson.toJson(turismo);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Turismo.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getComunidades() {
        String url = String.format("%s/comunidades", URL_API);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            return gson.fromJson(response.body(), listType);

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las comunidades: " + e.getMessage(), e);
        }
    }
}
