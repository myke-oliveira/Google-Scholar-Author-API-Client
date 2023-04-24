package com.myke.googlescholarauthorapiclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GoogleScholarAuthorAPI {
    public String request(String authorId) throws GoogleScholarAuthorAPIError {
        String apiKey = System.getenv("SERP_APIKEY");
        String engine = "google_scholar_author";
        String urlStr = String.format(
                "https://serpapi.com/search?engine=%s&author_id=%s&api_key=%s", 
                engine, 
                authorId, 
                apiKey
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(urlStr)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new GoogleScholarAuthorAPIError(e);
        }
    }
}
