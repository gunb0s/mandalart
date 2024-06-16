package com.gunb0s.plugins

import com.gunb0s.mandalart.createMandalartRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.mandalartRoutes() {
    routing {
        createMandalartRoute()
    }
}
