plugins {
    alias(libs.plugins.dokka)
    alias(libs.plugins.hildan.github.changelog)
    alias(libs.plugins.nexus.publish)
    // Commented out for now because of https://github.com/LouisCAD/CompleteKotlin/issues/15
//    alias(libs.plugins.louiscad.complete.kotlin) // for autocomplete of Apple libraries on non-macOS systems
    id("krossbow-githubinfo")

    // The following plugins are only added as a Workaround for https://github.com/gradle/gradle/issues/17559.
    // In short, we need to align the plugin classpath between all projects in the build.
    alias(libs.plugins.kotlin.atomicfu) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    `maven-publish`
    kotlin("multiplatform") version "2.0.21" apply false
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    group = "com.github.plato-corp"
    version = "1.0.0"

    // JVM 대상에 대해 Java 11 설정
    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    // Dokka 태스크 제외
    tasks.withType<org.jetbrains.dokka.gradle.DokkaTask> {
        enabled = false
    }

    // 서명 태스크 비활성화
    tasks.withType<Sign> {
        enabled = false
    }
}

//tasks.withType<org.jetbrains.dokka.gradle.DokkaMultiModuleTask>().configureEach {
//    outputDirectory.set(file("$rootDir/docs/kdoc"))
//}
//
//changelog {
//    githubUser = github.user
//    futureVersionTag = project.version.toString()
//    customTagByIssueNumber = mapOf(6 to "0.1.1", 10 to "0.1.2", 15 to "0.4.0")
//}

subprojects {
    afterEvaluate {
        if (name != "krossbow-websocket-test") {
            publishing {
                publications {
                    create<MavenPublication>("rootMaven") {
                        from(components["kotlin"])
                        groupId = project.group.toString()
                        artifactId = project.name
                        version = project.version.toString()
                    }
                }
            }
        }
    }
}
