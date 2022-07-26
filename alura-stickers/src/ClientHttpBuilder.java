import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttpBuilder {

    public String getApiResponse(String url) {
        // montando uma conexão HTTP para buscar dados da API
        try {

            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();

            // Criando request com metodo http GET, informando a URI
            var request = HttpRequest.newBuilder(endereco).GET().build();

            // response é a resposta dessa requisição enviada pelo cliente
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}