package com.gunb0s.mandalart

import com.gunb0s.ResponseDto
import com.gunb0s.mandalart.dto.CreateMainGoalRequest
import com.gunb0s.mandalart.dto.CreateMainGoalResponse
import com.gunb0s.mandalart.dto.CreateMandalartRequest
import com.gunb0s.mandalart.dto.CreateMandalartResponse
import com.gunb0s.mandalart.dto.CreateSubGoalRequest
import com.gunb0s.mandalart.dto.CreateSubGoalResponse
import com.gunb0s.mandalart.dto.MandalartDto
import com.gunb0s.mandalart.model.MainGoal
import com.gunb0s.mandalart.model.SubGoal
import com.gunb0s.mandalart.repository.MandalartRepository
import com.gunb0s.subGoalAction.dto.CreateSubGoalActionRequest
import com.gunb0s.subGoalAction.dto.CreateSubGoalActionResponse
import com.gunb0s.subGoalAction.model.SubGoalAction
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import java.time.LocalDateTime

fun Route.createMandalartRoute() {
    route("/mandalarts") {
        post {
            val request = call.receive<CreateMandalartRequest>()

            val mandalart = MandalartRepository.create(
                title = request.title,
                goal = request.goal
            )

            call.respond(
                HttpStatusCode.Created,
                ResponseDto(
                    data = CreateMandalartResponse(mandalart.id)
                )
            )
        }

        get("{id?}") {
            val id = call.parameters["id"]
                ?: throw IllegalArgumentException("Missing id")

            val mandalart = MandalartRepository.findById(id)
                ?: throw IllegalArgumentException("No mandalart with id $id")

            val dto = MandalartDto.fromModel(mandalart)

            call.respond(ResponseDto(data = dto))
        }

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
                        id,
                        mainGoal.location,
                        subGoal.location
                    )
                )
            )
        }

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
}
