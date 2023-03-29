package com.mycompany.alurastickers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class AluraStickers {

    public static void main(String[] args) throws IOException, InterruptedException, Exception {

        //Fazendo a conexão http
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI adress = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        System.out.println(body);
        
        //Extraindo dados (título, poster, classificação)
        //separando os elementos do json e implementando na List MoviesList
        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);
        //System.out.println(MoviesList.get(0));
        
         //Exibição e manipulação dos dados
         for (Map<String, String> movie : moviesList) {
             String urlImagem = movie.get("image");
             String urlName = movie.get("title");
             
             String fileName = "saida/"+ urlName + ".png";
             
             InputStream inputStream =  new URL(urlImagem).openStream();
             
             StickerGenerator generator = new StickerGenerator();
             generator.creat(inputStream, fileName);
             
             System.out.println(movie.get("title"));
        }
    }
}
