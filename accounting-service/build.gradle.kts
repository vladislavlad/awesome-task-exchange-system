import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "toughdevschool.ates"
version = "0.3.3"

repositories {
    mavenLocal()
    mavenCentral()
}

val springCloudVersion = "2023.0.0"
val platformVersion = "0.5.1"
val micrometerVersion = "1.2.4"

dependencies {
    implementation("toughdevschool.ates:schema-registry:0.1.0")
    implementation("software.darkmatter:interaction-messaging:$platformVersion")
    implementation("software.darkmatter:interaction-protocol:$platformVersion")
    implementation("software.darkmatter:platform-core:$platformVersion")
    implementation("software.darkmatter:security-core:$platformVersion")
    implementation("software.darkmatter:security-jwt-client-starter:$platformVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.3.0")

    // Micrometer dependencies
    implementation(platform("io.micrometer:micrometer-tracing-bom:$micrometerVersion"))
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing")

//    implementation("io.micrometer:micrometer-tracing-bridge-otel")
//    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

    // force proxy version
    implementation("io.r2dbc:r2dbc-proxy:1.1.4.RELEASE")
    // R2DBC micrometer auto tracing
    implementation("org.springframework.experimental:r2dbc-micrometer-spring-boot:1.0.2")


    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.hibernate:hibernate-validator:8.0.1.Final")

    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    implementation("io.arrow-kt:arrow-core:1.2.1")
    implementation("io.github.oshai:kotlin-logging:6.0.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation("io.projectreactor:reactor-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

java.sourceCompatibility = JavaVersion.VERSION_21

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = "21"
    }
}

springBoot {
    buildInfo()
}

tasks.jar { enabled = false }

tasks.bootJar {
    layered { enabled = true }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
