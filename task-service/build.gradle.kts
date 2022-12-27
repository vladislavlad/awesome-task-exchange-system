import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "toughdevschool.ates"
version = "0.0.2"
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

val springCloudVersion = "2022.0.0"
val platformVersion = "0.1.0"

dependencies {
    implementation("toughdevschool.ates:schema-registry:0.0.1")
    implementation("software.darkmatter:interaction-messaging:$platformVersion")
    implementation("software.darkmatter:interaction-protocol:$platformVersion")
    implementation("software.darkmatter:platform-core:$platformVersion")
    implementation("software.darkmatter:security-core:$platformVersion")
    implementation("software.darkmatter:security-jwt-client-starter:$platformVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.14")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.14")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
    implementation("io.arrow-kt:arrow-core:1.1.2")
    implementation("io.github.microutils:kotlin-logging:3.0.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core:9.10.1")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
        jvmTarget = "17"
    }
}

springBoot {
    buildInfo()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
