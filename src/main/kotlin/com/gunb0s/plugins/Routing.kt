package com.gunb0s.plugins

import com.gunb0s.ResponseDto
import com.gunb0s.mandalart.mandalartRouting
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                ResponseDto(data = null, message = "Failed", error = cause.message)
            )
        }
    }
    routing {
        mandalartRouting()
    }
}
