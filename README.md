# Awesome Task Exchange System (UberPopug Inc) 


## This project is being implemented as part of the course ["Asynchronous architecture"](https://education.borshev.com/architecture).

## Project Architecture
### Domain model and Event Storming result at [Miro Dashboard](https://miro.com/app/board/uXjVPMJI1FE=/?share_link_id=487431509477)


## This repo includes 

### Microservices:
#### Task service
* Auth: Bearer JWT
#### Accounting service 
* Auth: Bearer JWT
* [Swagger UI](http://localhost:8082/swagger-ui.html)
#### Analytics service
* Auth: Bearer JWT

### Env Docker Compose:
#### Auth service
* Auth method: JWT
* Dockerhub image: [vladislavlad/accounts](https://hub.docker.com/repository/docker/vladislavlad/accounts)
* [Swagger UI](http://localhost:8080/swagger-ui.html)
#### Kafka
* Schema regestry 
#### [Kafka UI](http://localhost:8090)
#### PostgreSQL
#### ScyllaDB


## Stack
* Kotlin 1.7
* Spring Boot 2.7.x (WebFlux, R2DBC, Cloud Stream)
* Kafka
* PostgreSQL
* ScyllaDB

### Stack is fully reactive
Spring WebFlux is base for reactive Spring RestAPI apps. WebFlux core is [project Reactor](https://github.com/reactor/reactor-core) and async non-blocking [netty server](https://github.com/netty/netty).<br> 
[Spring Data R2DBC](https://github.com/spring-projects/spring-data-r2dbc) is reactive connection to relational databases. In this project I use PostgreSQL DB and [R2DBC driver](https://github.com/pgjdbc/r2dbc-postgresql).<br>
Next part is Spring Cloud Stream with Kafka Binder. Spring Cloud Stream allows to define [reactive functions](https://cloud.spring.io/spring-cloud-stream/spring-cloud-stream.html#_reactive_functions_support) connected with Kafka consumers and producers.<br> 
[Spring Reactive Cassandra](https://github.com/spring-projects/spring-data-cassandra/blob/main/src/main/asciidoc/reference/reactive-cassandra.adoc) is using for interaction with ScyllaDB.<br> 
And, finally, [Kotlin coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/reactive/kotlinx-coroutines-reactor/README.md) allows us to perfectly connect everything together.
