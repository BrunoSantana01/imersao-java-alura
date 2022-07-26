import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // IMdB
        // String url = "https://api.mocki.io/v2/549a5d8b"; // mock
        // String url = "https://imdb-api.com/en/API/Top250Movies/[key] 
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMdB();

        // NASA
        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=[key]f&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        // Api Local de Linguagens
        // String url = "http://localhost:8080/linguagens";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMdB();

        // Heroku deployed "linguagens" API
        String url = "https://meualura-linguagens-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMdB();

        var httpClient = new ClientHttpBuilder();
        String json = httpClient.getApiResponse(url);

        // extrair dados(titulo, poster, classificação)
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // instancia fabrica de figurinhas
        var stickerFactory = new StickerFactory();

        // exibir e manipular dados
        for (int i = 0; i < 10; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            stickerFactory.createSticker(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
