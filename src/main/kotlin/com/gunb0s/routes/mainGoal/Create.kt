package com.gunb0s.routes.mainGoal

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.routes.mainGoal.dto.CreateMainGoalRequest
import com.gunb0s.routes.mainGoal.dto.CreateMainGoalResponse
import com.gunb0s.model.MainGoal
import com.gunb0s.repository.MandalartRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createMainGoalRoute() {
    post("{id}/main-goals") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing id")

        val mandalart = MandalartRepository.findById(id)
            ?: throw IllegalArgumentException("No mandalart with id $id")

        val (location, goal) = call.receive<CreateMainGoalRequest>()

        val mainGoal = MainGoal(location, goal)

        mandalart.addMainGoal(mainGoal)

        call.respond(
            HttpStatusCode.Created,
            ResponseDto(
                data = CreateMainGoalResponse(
                    id,
                    mainGoal.location
                )
            )
        )
    }
}
