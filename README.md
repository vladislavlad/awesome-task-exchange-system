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
* In progress


## Stack
* Kotlin 2.1
* Spring Boot 3.4.x (WebFlux, R2DBC, Cloud Stream)
* Kafka
* PostgreSQL 17
* ScyllaDB 5
* Redis 7

### Reactive Stack

Tech stack is fully reactive, leveraging the following key components:
* Spring WebFlux: The foundation for building reactive Spring RestAPI applications, powered by [Project Reactor](https://github.com/reactor/reactor-core) and async, non-blocking [Netty server](https://github.com/netty/netty).
* [Spring Data R2DBC](https://github.com/spring-projects/spring-data-r2dbc): Allows reactive connections to relational databases. This project uses PostgreSQL DB with the [R2DBC driver](https://github.com/pgjdbc/r2dbc-postgresql).
* Spring Cloud Stream with Kafka Binder: Facilitates connection to Kafka, allowing definition of [reactive functions](https://cloud.spring.io/spring-cloud-stream/spring-cloud-stream.html#_reactive_functions_support) to perfome fluent integration with Kafka consumers and producers.
* [Spring Reactive Cassandra](https://github.com/spring-projects/spring-data-cassandra/blob/main/src/main/asciidoc/reference/reactive-cassandra.adoc): Handles interactions with [ScyllaDB](https://github.com/scylladb/scylladb).
* [Kotlin coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/reactive/kotlinx-coroutines-reactor/README.md): Seamlessly ties everything together, enabling efficient and pretty connections between components.
