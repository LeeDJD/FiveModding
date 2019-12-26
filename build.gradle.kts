plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "space.kappes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.apache.logging.log4j","log4j-core","2.13.0")
    implementation("net.dv8tion", "JDA", "4.1.0_86")
    implementation("org.json", "json", "20190722")
    testImplementation("junit", "junit", "4.12")
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
                "Main-Class" to "space.kappes.FiveModding.FiveBot"
        ))
    }
}