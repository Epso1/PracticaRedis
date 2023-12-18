import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Scanner;

public class UrlShortenerClient {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("redis://localhost:6379");
        Scanner scanner = new Scanner(System.in);
        String listKey ="CESAR1:URLS_TO_SHORT";

        while (true) {
            System.out.println("Ingrese un comando: ");
            String input = scanner.nextLine();

            if (input.startsWith("shorten")) {
                // Comando: shorten URL
                String[] tokens = input.split(" ");
                if (tokens.length == 2) {
                    String urlToShorten = tokens[1];
                    jedis.rpush(listKey, urlToShorten);
                    System.out.println("URL enviada para acortar.");

                    // Obtener todos los elementos de la lista
                    List<String> urlsToShort = jedis.lrange(listKey, 0, -1);

                    // Imprimir cada URL
                    for (String url : urlsToShort) {
                        System.out.println(url);
                    }

                } else {
                    System.out.println("Formato incorrecto. Uso: shorten URL");
                }
            } else if (input.equals("exit")) {
                // Comando: exit
                System.out.println("Saliendo del programa.");
                break;
            } else if (input.startsWith("http://")) {
                // Comando: http://SHORTEDURL
                String[] tokens = input.split(" ");
                if(tokens.length == 2){
                    String originalUrl = tokens[0];
                    String shortedUrl = tokens[1];
                    if(originalUrl.startsWith("http://")){
                        originalUrl = jedis.hget(listKey, originalUrl);
                        System.out.println("URL original: " + originalUrl);
                    } else{
                        System.out.println("Formato incorrecto. Uso: URL SHORTEDURL");
                    }

                }else {
                    System.out.println("Formato incorrecto. Uso: URL SHORTEDURL");
                }

            } else {
                System.out.println("Comando no reconocido.");
            }
        }

        jedis.close();
        scanner.close();
    }
}
