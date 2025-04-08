plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "toughdevschool.ates"
version = "0.5.0"

repositories {
    mavenLocal()
    mavenCentral()
}

val springCloudVersion = "2024.0.1"
val springdocUiVersion = "2.8.6"
val platformVersion = "0.7.3"
val schemaRegistryVersion = "0.2.3"
val kotestVersion = "5.9.1"
val mockkVersion = "1.13.13"

dependencies {
    implementation("toughdevschool.ates:schema-registry:$schemaRegistryVersion")
    implementation("software.darkmatter:interaction-messaging:$platformVersion")
    implementation("software.darkmatter:interaction-protocol:$platformVersion")
    implementation("software.darkmatter:platform-core:$platformVersion")
    implementation("software.darkmatter:platform-data:$platformVersion")
    implementation("software.darkmatter:security-core:$platformVersion")
    implementation("software.darkmatter:security-jwt-client-starter:$platformVersion")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:$springdocUiVersion")

    // Micrometer dependencies
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

    // for R2DBC query tracing
    implementation("io.r2dbc:r2dbc-proxy:1.1.5.RELEASE")


    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("org.hibernate:hibernate-validator:8.0.2.Final")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    implementation("io.arrow-kt:arrow-core:2.0.1")
    implementation("io.github.oshai:kotlin-logging:7.0.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")

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
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:2.0.0")
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

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions{
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
    }
    target {
        java.targetCompatibility = JavaVersion.VERSION_21
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
