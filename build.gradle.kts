plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    `maven-publish`
    signing
}

val githubRepo = "MUtils-MC/MChallenge-API"

repositories {
    mavenCentral()
}

dependencies {
    val adventureVersion = "4.13.1"
    compileOnly("net.kyori:adventure-api:$adventureVersion")
    compileOnly("net.kyori:adventure-text-minimessage:$adventureVersion")
    compileOnly("net.kyori:adventure-text-serializer-plain:$adventureVersion")
    compileOnly("net.kyori:adventure-text-serializer-gson:$adventureVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    register("release") {
        group = "publishing"
        dependsOn("publish")
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

publishing {
    repositories {
        maven {
            name = "ossrh"
            credentials(PasswordCredentials::class)
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "de.miraculixx"
            artifactId = "challenge-api"
            version = "1.5.0"

            from(components["java"])

            pom {
                name.set("MChallenge-API")
                description.set("Access MChallenge through this API")
                url.set("https://mutils.net/ch")

                developers {
                    developer {
                        id.set("miraculixx")
                        name.set("Miraculixx")
                        email.set("miraculixxyt@gmail.com")
                    }
                }

                licenses {
                    license {
                        name.set("GNU Affero General Public License v3.0")
                        url.set("https://www.gnu.org/licenses/agpl-3.0.en.html")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/${githubRepo}.git")
                    url.set("https://github.com/${githubRepo}")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}
