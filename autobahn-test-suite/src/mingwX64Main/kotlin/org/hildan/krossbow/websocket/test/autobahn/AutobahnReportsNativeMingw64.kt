package org.hildan.krossbow.websocket.test.autobahn

import io.ktor.client.engine.*
import io.ktor.client.engine.winhttp.*

actual fun ktorEngine(): HttpClientEngineFactory<*> = WinHttp
