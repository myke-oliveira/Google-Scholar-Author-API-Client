import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
  public static void main(String[] args) {
    String  authorId = "EicYvbwAAAAJ";
    String apiKey = System.getenv("SERP_APIKEY");
    String engine = "google_scholar_author";
    String urlStr = String.format("https://serpapi.com/search?engine=%s&author_id=%s&api_key=%s", engine, authorId, apiKey);

    System.out.println(urlStr);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(URI.create(urlStr)).build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());
    } catch (IOException e) {
      System.out.println(e);
    } catch (InterruptedException e) {
      System.out.println(e);
    }


  }
}
