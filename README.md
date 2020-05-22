# Vert.x

### PODSUMOWANIE

#### APLIKACJA VERTX
Utworzenie 2 aplikacji z użyciem Vert.x:

- aplikacja Vertx komunikująca się z klastrem Kafki,
- aplikacja Vertx wystawiająca REST api z połączeniem do bazy danych PostgreSQL.

##### Zalety:

- nieblokujący/asynchroniczny serwer http,
- nieblokujący/asynchroniczny sterownik do Postgres'a,
- możliwość wpięcia do Spring Boot'a, ale z pozostawieniem serwera http Vertex'owego w celu zachowania asynchrnoczności Vertex'a.

##### Niedogodności:

- odmienne podejście do DI - wymiana danych pomiędzy 'Verticles' poprzez 'event bus',
- brak ORM - z pomocą może przyjść 'jOOQ',
- reaktywny sterownik do Postgres'a wymaga pisania zapytań w czystym SQL (dostępne prepared statement),
- dane przesyłane przez 'event bus' muszą być w formie tekstowej (String, JSON),
- brak adnotacji jak w Springu (definiowanie endpoint'ów, ustawianie header'ów)



#### TESTY WYDAJNOŚCIOWE

- Testy wykonane w Jmeter.
- Porównanie czasów wykonania request'ów do API (GET, POST) pomiędzy ap1likacją Micronaut'ową a Vertx.
- operacja GET pobierająca wszystkie rekordy z tabeli
- operacja POST dodająca nowy rekord do tabeli.

##### Scenariusz testowy:

- dodanie rekordu - POST w Vertx
- dodanie rekordu - POST w Micronaut
- pobranie rekoród - GET w Vertx
- pobranie rekoród - GET w Micronaut

##### Parametry testu:

- 20 użytkowników
- 2 sekwencje

##### TESTY - WNIOSKI:

- aplikacja Vertx wykazuje się większą wydajność o conajmniej 30% względem Micronaut'owej niezależnie od rodzaju zapytania.



### Building

- maven
- gradle



### Security

- JWT auth
- Apache Shiro
- OAuth 2
- JDBC auth
- MongoDB auth
- .htdigest Auth



### Web

- GraphQL
- OpenAPI 3



### Web UI

- Kue UI
- Handlebars
- Jade,
- MVEL
- Thymeleaf
- Apache FreeMarker
- Pebble
- Rocker



### Database connectors

- PostgreSQL

- MySQL

- MongoDB

- JDBC

- Redis

- Cassandra



### Messaging

- AMQP

- RabbitMQ

- Kafka
- STOMP



### Metrics

- Dropwizard
- Micrometer
- Vert.x Health Check
- Shell - allows interaction with Vert.x app using CLI
