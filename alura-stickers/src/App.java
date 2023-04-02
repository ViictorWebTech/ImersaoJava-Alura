import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) throws Exception {
        /*
         Press Alt+Enter with your caret at the highlighted text to see how
         IntelliJ IDEA suggests fixing it.
         Press Shift+F10 or click the green arrow button in the gutter to run the code.
         */

        // Fazer uma conexão HTTP e buscar os filmes mais populares
        // API do top 250 filmes mais populares:
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // API do top 250 séries: String url = "https://imdb-api.com/en/API/Top250TVs/api";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Pegar só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        for (int f = 0; f < 5; f++) {

            Map<String, String> filme = ListaDeFilmes.get(f);
            String fundoMagenta = "\u001B[45m";
            String negrito = "\u001B[1m";
            String reset = "\u001B[0m";
            String estrela = "⭐";
            double rating = Double.parseDouble(filme.get("imDbRating"));

            System.out.println(negrito + "Título: " + reset + filme.get("title"));
            System.out.println("Poster URL: " + filme.get("image"));
            System.out.println(fundoMagenta + "Classificação" + rating + reset);
            for (int i = 1; i <= rating; i++) {
                System.out.print(estrela);

            }
            System.out.println("\n");
        }
    }
}

