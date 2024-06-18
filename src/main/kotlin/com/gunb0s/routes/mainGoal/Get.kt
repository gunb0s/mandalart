package com.gunb0s.routes.mainGoal

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.repository.MandalartRepository
import com.gunb0s.routes.mainGoal.dto.MainGoalDto
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getMainGoalRoute() {
    get("{id}/main-goals/{mainGoalLocation}") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing id")
        val mainGoalLocation = call.parameters["mainGoalLocation"]
            ?.toInt()
            ?: throw IllegalArgumentException("Missing mainGoalLocation")

        val mainGoal = MandalartRepository.findMainGoalByLocation(id, mainGoalLocation)
            ?: throw IllegalArgumentException("No MainGoal with location $mainGoalLocation")

        val dto = MainGoalDto.fromModel(mainGoal)

        call.respond(
            HttpStatusCode.OK,
            ResponseDto(data = dto)
        )
    }
}
