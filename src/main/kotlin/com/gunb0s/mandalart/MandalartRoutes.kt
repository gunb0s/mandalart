package com.gunb0s.mandalart

import com.gunb0s.mandalart.dto.CreateMainGoalRequest
import com.gunb0s.mandalart.dto.CreateMainGoalResponse
import com.gunb0s.mandalart.dto.CreateMandalartRequest
import com.gunb0s.mandalart.dto.CreateMandalartResponse
import com.gunb0s.mandalart.dto.MandalartDto
import com.gunb0s.mandalart.model.MainGoal
import com.gunb0s.mandalart.repository.MandalartRepository
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

            val dto = MandalartDto.fromModel(mandalart)

            call.respond(dto)
        }

        post("{id}/main-goals") {
            val id = call.parameters["id"]
                ?: return@post call.respondText(
                    "Missing id", status = HttpStatusCode.BadRequest
                )

            val mandalart = MandalartRepository.findById(id)
                ?: return@post call.respondText(
                    "No mandalart with id $id",
                    status = HttpStatusCode.NotFound
                )

            val (row, col, goal) = call.receive<CreateMainGoalRequest>()

            val mainGoal = MainGoal(row, col, goal)

            mandalart.addMainGoal(mainGoal)

            call.respond(
                CreateMainGoalResponse(
                    id,
                    mainGoal.row,
                    mainGoal.col
                )
            )
        }

        post("{id}/main-goals/{mainGoalId}/sub-goals") {
            call.respondText { "Sub goals" }
        }
    }
}
