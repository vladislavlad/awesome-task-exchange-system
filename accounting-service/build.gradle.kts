import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "toughdevschool.ates"
version = "0.3.7"

repositories {
    mavenLocal()
    mavenCentral()
}

val springCloudVersion = "2023.0.3"
val platformVersion = "0.5.11"
val micrometerVersion = "1.3.2"
val kotestVersion = "5.9.1"
val mockkVersion = "1.13.13"

dependencies {
    implementation("toughdevschool.ates:schema-registry:0.1.3")
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
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")

    // Micrometer dependencies
    implementation(platform("io.micrometer:micrometer-tracing-bom:$micrometerVersion"))
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

    // for R2DBC query tracing
    implementation("io.r2dbc:r2dbc-proxy:1.1.5.RELEASE")

//    implementation("io.micrometer:micrometer-tracing-bridge-otel")
//    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    implementation("org.hibernate:hibernate-validator:8.0.1.Final")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    implementation("io.arrow-kt:arrow-core:1.2.4")
    implementation("io.github.oshai:kotlin-logging:7.0.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.micrometer:micrometer-observation-test")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")
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
