plugins {
    kotlin("multiplatform")
    `kotlin-maven-central-publish`
}

description = "Multiplatform SockJS implementation of Krossbow's WebSocket API."

kotlin {
    jvm()
    jsTargets()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.krossbowWebsocketCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                api(projects.krossbowWebsocketSpring)
            }
        }
        val jsMain by getting {
            dependencies {
                api(projects.krossbowWebsocketBuiltin)
                implementation(npm("sockjs-client", libs.versions.npm.sockjs.client.get()))
            }
        }
    }
}
