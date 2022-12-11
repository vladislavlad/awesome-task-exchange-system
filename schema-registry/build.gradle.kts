import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    `java-library`
    `maven-publish`
}

group = "toughdevschool.ates"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

val platformVersion = "0.0.7"
val kotestVersion = "5.5.4"

dependencies {
    implementation("software.darkmatter:interaction-protocol:$platformVersion")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

tasks.jar {
    enabled = true
    archiveClassifier.set("")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
        jvmTarget = "17"
    }
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
