package com.gunb0s.routes.subGoalAction

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.repository.MandalartRepository
import com.gunb0s.routes.subGoalAction.dto.CreateSubGoalActionRequest
import com.gunb0s.routes.subGoalAction.dto.CreateSubGoalActionResponse
import com.gunb0s.model.SubGoalAction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.createSubGoalActionRoute() {
    post("{id}/main-goals/{mainGoalLocation}/sub-goals/{subGoalLocation}/sub-goal-actions") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing Mandalart id")
        val mainGoalLocation = call.parameters["mainGoalLocation"]
            ?.toInt()
            ?: throw IllegalArgumentException("Missing MainGoal location")
        val subGoalLocation = call.parameters["subGoalLocation"]
            ?.toInt()
            ?: throw IllegalArgumentException("Missing SubGoal location")

        val subGoal = MandalartRepository.findSubGoalByLocation(id, mainGoalLocation, subGoalLocation)
            ?: throw IllegalArgumentException("No SubGoal with location $subGoalLocation")

        val (action) = call.receive<CreateSubGoalActionRequest>()
        val subGoalAction = SubGoalAction(
            createdDate = LocalDateTime.now(),
            action = action
        )
        subGoal.addSubGoalAction(subGoalAction)

        call.respond(
            HttpStatusCode.Created,
            ResponseDto(
                data = CreateSubGoalActionResponse(
                    id,
                    mainGoalLocation,
                    subGoalLocation,
                    subGoalAction.createdDate,
                    subGoalAction.action
                )
            )
        )
    }
}
