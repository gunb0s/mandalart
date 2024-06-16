package com.gunb0s.mandalart

import com.gunb0s.mandalart.dto.CreateMandalartRequest
import com.gunb0s.mandalart.dto.CreateMandalartResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.mandalartRouting() {
    route("/mandalarts") {
        post {
            val request = call.receive<CreateMandalartRequest>()

            val mandalart = MandalartRepository.create(
                title = request.title,
                goal = request.goal
            )

            call.respond(CreateMandalartResponse(mandalart.id))
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
