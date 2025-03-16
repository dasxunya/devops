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
        languageVersion = JavaLanguageVersion.of(21)
    }
}

sourceSets {
    main{
        java {
            srcDir("/devops/src/main")
        }
    }
}

repositories{
    mavenCentral()
}

buildscript {
    dependencies{
        classpath("org.postgresql:postgresql:42.7.5")
        classpath("org.flywaydb:flyway-database-postgresql:11.4.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.flywaydb:flyway-database-postgresql:11.4.0")
    implementation("org.postgresql:postgresql:42.7.5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok:1.18.36")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("getCredential"){

}

flyway {
    driver = "org.postgresql.Driver"
    url = project.property("dbUrl").toString()
    user = project.property("dbUser").toString()
    password = project.property("dbPassword").toString()
    schemas = arrayOf(project.property("dbSchema").toString())
}