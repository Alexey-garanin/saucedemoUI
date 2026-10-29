plugins {
    kotlin("jvm") version "2.3.0"
    id("io.qameta.allure") version "3.0.1"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.microsoft.playwright:playwright:1.50.0")


    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    testImplementation("io.qameta.allure:allure-junit5:2.25.0")
    testImplementation("io.qameta.allure:allure-rest-assured:2.25.0")


    testImplementation("org.assertj:assertj-core:3.26.3")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
    systemProperty("playwright.headless", "true")
    systemProperty("allure.results.directory", "build/allure-results")
}

allure {
    version.set("2.25.0")
}


