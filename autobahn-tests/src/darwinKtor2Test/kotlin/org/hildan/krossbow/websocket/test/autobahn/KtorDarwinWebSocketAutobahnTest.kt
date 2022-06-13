package org.hildan.krossbow.websocket.test.autobahn

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.websocket.*
import org.hildan.krossbow.websocket.WebSocketClient
import org.hildan.krossbow.websocket.ktor.KtorWebSocketClient

class KtorDarwinWebSocketAutobahnTest : AutobahnClientTestSuite(
    agentUnderTest = "krossbow-ktor-darwin-client-${Platform.osFamily.name.lowercase()}"
) {

    override fun provideClient(): WebSocketClient = KtorWebSocketClient(HttpClient(Darwin) { install(WebSockets) })
}
