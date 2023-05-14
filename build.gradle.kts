plugins {
//    kotlin("jvm") version "1.8.20"
//    kotlin("plugin.serialization") version "1.8.20"
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
    signing
}

group = "de.miraculixx.challenges.api"
setProperty("module_name", "challenges")

val githubRepo = "MUtils-MC/MChallenge-API"
val isSnapshot = false

repositories {
    mavenCentral()
}

val adventureVersion = "4.13.1"
dependencies {
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
            setUrl(
                if (!isSnapshot) "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
                else "https://s01.oss.sonatype.org/content/repositories/snapshots"
            )
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "de.miraculixx"
            artifactId = "challenge-api"
            version = "1.3.0"

            from(components["java"])

            pom {
                name.set("MUtils-Challenge-API")
                description.set("Access MUtils-Challenge through this API")
                url.set("https://mutils.net")

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
                    url.set("https://github.com/${githubRepo}/tree/master/timer")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}
