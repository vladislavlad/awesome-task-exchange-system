# Awesome Task Exchange System (UberPopug Inc) 


## This project is being implemented as part of the course ["Asynchronous architecture"](https://education.borshev.com/architecture).

## Project Architecture
### Domain model and Event Storming result at [Miro Dashboard](https://miro.com/app/board/uXjVMO_RL2A=/)


## This repo includes 

### Microservices:
#### Task service
* Auth: Bearer JWT
* [Swagger UI](http://localhost:8081/swagger-ui.html)
#### Accounting service 
* Auth: Bearer JWT
* [Swagger UI](http://localhost:8082/swagger-ui.html)
#### Analytics service
* Auth: Bearer JWT
* [Swagger UI](http://localhost:8083/swagger-ui.html)

### Env Docker Compose:
#### Auth service
* Authn method: Username + Password
* Auth type: Bearer JWT
* Dockerhub image: [vladislavlad/accounts](https://hub.docker.com/repository/docker/vladislavlad/accounts)
* [Swagger UI](http://localhost:8080/swagger-ui.html)
#### Kafka
* version 3.5 KRaft (without Zookeeper) 
#### [Kafka UI](http://localhost:8090)
#### [Zipkin](http://localhost:9411)
#### PostgreSQL
#### ScyllaDB
#### Redis

### UI
* TODO


## Stack
* Kotlin 1.9
* Spring Boot 3.3.x (WebFlux, R2DBC, Cloud Stream)
* Kafka
* PostgreSQL 16
* ScyllaDB 5
* Redis 7

### Stack is fully reactive
Spring WebFlux is base for reactive Spring RestAPI apps. WebFlux core is [project Reactor](https://github.com/reactor/reactor-core) and async non-blocking [netty server](https://github.com/netty/netty).<br> 
[Spring Data R2DBC](https://github.com/spring-projects/spring-data-r2dbc) is reactive connection to relational databases. In this project I use PostgreSQL DB and [R2DBC driver](https://github.com/pgjdbc/r2dbc-postgresql).<br>
Next part is Spring Cloud Stream with Kafka Binder. Spring Cloud Stream allows to define [reactive functions](https://cloud.spring.io/spring-cloud-stream/spring-cloud-stream.html#_reactive_functions_support) connected with Kafka consumers and producers.<br> 
[Spring Reactive Cassandra](https://github.com/spring-projects/spring-data-cassandra/blob/main/src/main/asciidoc/reference/reactive-cassandra.adoc) is using for interaction with ScyllaDB.<br> 
And, finally, [Kotlin coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/reactive/kotlinx-coroutines-reactor/README.md) allows us to perfectly connect everything together.
