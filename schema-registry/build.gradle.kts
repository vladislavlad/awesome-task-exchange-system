plugins {
    kotlin("jvm") version "2.1.0"
    `java-library`
    `maven-publish`
}

group = "toughdevschool.ates"
version = "0.2.2"

repositories {
    mavenLocal()
    mavenCentral()
}

val platformVersion = "0.7.1"
val kotestVersion = "5.9.2"

dependencies {
    implementation("software.darkmatter:interaction-protocol:$platformVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.jar {
    enabled = true
    archiveClassifier.set("")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    withSourcesJar()
}

kotlin {
    compilerOptions.freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
    target {
        java.targetCompatibility = JavaVersion.VERSION_21
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
