package com.gunb0s.routes.subGoal

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.model.SubGoal
import com.gunb0s.repository.MandalartRepository
import com.gunb0s.routes.subGoal.dto.CreateSubGoalRequest
import com.gunb0s.routes.subGoal.dto.CreateSubGoalResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.createSubGoalRoute() {
    post("{id}/main-goals/{mainGoalLocation}/sub-goals") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing Mandalart id")
        val mainGoalLocation = call.parameters["mainGoalLocation"]
            ?.toInt()
            ?: throw IllegalArgumentException("Missing MainGoal location")

        val mainGoal = MandalartRepository.findMainGoalByLocation(id, mainGoalLocation)
            ?: throw IllegalArgumentException("No MainGoal with location $mainGoalLocation")

        val (location, goal) = call.receive<CreateSubGoalRequest>()
        val subGoal = SubGoal(location, goal)

        mainGoal.addSubGoal(subGoal)

        call.respond(
            HttpStatusCode.Created,
            ResponseDto(
                data = CreateSubGoalResponse(
                    subGoal.location,
                    subGoal.goal
                )
            )
        )
    }
}
