package com.demo.mealorder.ktor

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun runServer() {
    embeddedServer(Netty, 8082) {
        routing {
            get("/") {
                call.respondText("Hello from Ktor!")
            }
        }
    }.start(wait = true)
}