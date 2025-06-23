package org.DIS.practica2.servicios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.DIS.practica2.modelos.Turismo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.net.URI;
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

}
