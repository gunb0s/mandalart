package com.gunb0s.plugins

import com.gunb0s.mandalart.mandalartRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        mandalartRouting()
    }
}
