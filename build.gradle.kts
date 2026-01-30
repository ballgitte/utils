plugins {
    `java-library`
    `maven-publish`
}

group = "dev.ballgitte"
version = "1.4.2"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "dev.ballgitte"
            artifactId = "utils"
            version = "1.4.2"
        }
    }
    repositories {
        maven {
            name = "localRepo"
            url = uri(System.getProperty("user.home") + "/.m2/repository")
        }
    }
}
