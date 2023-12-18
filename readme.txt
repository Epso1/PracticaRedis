PracticaRedis

Descripción
PracticaRedis es una aplicación Java que utiliza Redis para acortar URLs. La aplicación consta de dos partes principales: UrlShortenerClient y UrlShortenerService.  UrlShortenerClient permite a los usuarios ingresar URLs para acortar y las agrega a una lista en Redis. UrlShortenerService verifica continuamente esta lista para acortar cualquier URL pendiente y almacena la URL acortada y la URL original en una tabla hash en Redis.

Requisitos
Java JDK 18.0.2.1
Maven
Redis
IntelliJ IDEA 2023.2.2

Instalación
Clona el repositorio de GitHub.
Navega hasta el directorio del proyecto.
Ejecuta mvn install para instalar las dependencias.

Uso
Ejecuta UrlShortenerClient y UrlShortenerService en 2 terminales diferentes desde el botón "run" del IDE o con los siguientes comandos:
Para ejecutar UrlShortenerClient, usa el siguiente comando:
java -cp target/classes;C:\Users\cesar\.m2\repository\redis\clients\jedis\4.3.1\jedis-4.3.1.jar UrlShortenerClient
Para ejecutar UrlShortenerService, usa el siguiente comando:
java -cp target/classes;C:\Users\cesar\.m2\repository\redis\clients\jedis\4.3.1\jedis-4.3.1.jar UrlShortenerService