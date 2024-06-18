package com.gunb0s.routes.subGoal

import com.gunb0s.repository.MandalartRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getSubGoalRoute() {
    get("{id}/main-goals/{mainGoalLocation}/sub-goals/{subGoalLocation}") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing id")
        val mainGoalLocation = call.parameters["mainGoalLocation"]
            ?.toInt()
            ?: throw IllegalArgumentException("Missing mainGoalLocation")
        val subGoalLocation = call.parameters["subGoalLocation"]
            ?.toInt()
            ?: throw IllegalArgumentException("Missing subGoalLocation")

        val subGoal = MandalartRepository.findSubGoalByLocation(id, mainGoalLocation, subGoalLocation)
            ?: throw IllegalArgumentException("No SubGoal with location $subGoalLocation")

//        val dto = SubGoalDto.fromModel(subGoal)

        call.respond(
            HttpStatusCode.OK,
//            ResponseDto(data = dto)
        )
    }
}
