import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

public class UrlShortenerService {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("redis://localhost:6379");
        String listKey ="CESAR1:URLS_TO_SHORT";
        String listkey2 = "CESAR1:SHORTED_URLS";


        while (true) {
            // Verificar si hay direcciones pendientes en el listado y acortarlas
            String urlToShorten = jedis.lpop(listKey);
            if (urlToShorten != null) {
                // Generar una cadena aleatoria de 8 caracteres
                String key = generateRandomString(8);
                // Almacenar la relación entre la URL acortada y la original en la tabla HASH
                jedis.hset(listKey, key, urlToShorten);

                // Obtener todos los elementos de la lista
                List<String> listaCompleta = jedis.lrange(listkey2, 0, -1);

                // Encontrar el índice del elemento deseado
                String elementoDeseado = key;

                int indice = listaCompleta.indexOf(elementoDeseado);

                // Mostrar el índice encontrado
                System.out.println("Índice de '" + elementoDeseado + "': " + indice);


                String shortedUrl = jedis.lpop(listkey2);
                System.out.println("URL acortada: " + shortedUrl);
            }

            // Esperar un tiempo antes de verificar nuevamente
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }
}
