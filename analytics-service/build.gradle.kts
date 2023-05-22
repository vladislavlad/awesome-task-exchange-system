import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "toughdevschool.ates"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

val springCloudVersion = "2022.0.1"
val platformVersion = "0.2.0"
val micrometerVersion = "1.0.4"

dependencies {
    implementation("toughdevschool.ates:schema-registry:0.0.2")
    implementation("software.darkmatter:interaction-messaging:$platformVersion")
    implementation("software.darkmatter:interaction-protocol:$platformVersion")
    implementation("software.darkmatter:platform-core:$platformVersion")
    implementation("software.darkmatter:security-core:$platformVersion")
    implementation("software.darkmatter:security-jwt-client-starter:$platformVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.4")

    // Micrometer dependencies
    implementation(platform("io.micrometer:micrometer-tracing-bom:$micrometerVersion"))
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing")

//    implementation("io.micrometer:micrometer-tracing-bridge-otel")
//    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

    // force proxy version
    implementation("io.r2dbc:r2dbc-proxy:1.1.0.RELEASE")
    // R2DBC micrometer auto tracing
    implementation("org.springframework.experimental:r2dbc-micrometer-spring-boot:1.0.2")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
    implementation("io.arrow-kt:arrow-core:1.1.5")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core:9.10.2")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation("io.projectreactor:reactor-test")
}

ext {
    // Remove after Spring Security upgrade
    set("snakeyaml.version", "1.33")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
