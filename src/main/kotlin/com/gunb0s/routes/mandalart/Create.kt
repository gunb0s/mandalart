package com.gunb0s.routes.mandalart

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.routes.mandalart.dto.CreateMandalartRequest
import com.gunb0s.routes.mandalart.dto.CreateMandalartResponse
import com.gunb0s.repository.MandalartRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createMandalartRoute() {
    post {
        val request = call.receive<CreateMandalartRequest>()

        val mandalart =
            MandalartRepository.create(
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
}
