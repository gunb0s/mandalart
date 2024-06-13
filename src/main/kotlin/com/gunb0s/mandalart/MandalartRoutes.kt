package com.gunb0s.mandalart

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.mandalartRouting() {
    route("/mandalarts") {
        post {
            val mandalart = call.receive<Mandalart>()

            call.respondText { "Created" }
        }
        get("{id?}") {
            val id = call.parameters["id"]
                ?: return@get call.respondText(
                    "Missing id", status = HttpStatusCode.BadRequest
                )

            val mandalart = MandalartRepository.findById(id)
                ?: return@get call.respondText(
                "No mandalart with id $id",
                status = HttpStatusCode.NotFound
            )

            call.respond(mandalart)
        }

        post("{id}/main-goals") {
            call.respondText { "Main goals" }
        }

        post("{id}/main-goals/{mainGoalId}/sub-goals") {
            call.respondText { "Sub goals" }
        }
    }
}
