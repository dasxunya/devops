plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.flywaydb.flyway") version "11.4.0"
}

group = "itmo"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.postgresql:postgresql:42.7.5")
        classpath("org.flywaydb:flyway-database-postgresql:11.4.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.flywaydb:flyway-database-postgresql:11.4.0")
    implementation("org.postgresql:postgresql:42.7.5")
    // https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.3")
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    driver = "org.postgresql.Driver"
    url = project.property("dbUrl").toString()
    user = project.property("dbUser").toString()
    password = project.property("dbPassword").toString()
    schemas = arrayOf(project.property("dbSchema").toString())
}